package main.service;

import main.model.User;
import main.repository.UserRepository;
import main.aux.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Override
    public Notification<User> loadByNameAndPassword(String username, String password) {
        String encodedPassword = encodePassword(password);
        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(username, encodedPassword);
        Notification<User> result = new Notification<>();
        if (optionalUser.isPresent()){
            result.setResult(optionalUser.get());
        }
        else{
            result.addError("username and / or password not found");
        }
        return result;
    }

    @Override
    public Notification<Boolean> register(User user){
        Notification<Boolean> registerNotification = new Notification<>();
        String plainPassword = user.getPassword();
        user.setPassword(AuthenticationServiceImpl.encodePassword(plainPassword));
        if (userRepository.findByUsernameAndPassword(user.getName(), user.getPassword()).isPresent()){
            registerNotification.addError("username " + user.getName() + " already in use");
            registerNotification.setResult(Boolean.FALSE);
        }
        else {
            Notification<Boolean> saveNotification = userService.save(user);
            registerNotification.setResult(saveNotification.getResult());
            registerNotification.addError(saveNotification.getFormattedErrors());
        }
        user.setPassword(plainPassword);
        return registerNotification;
    }

    public static String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
