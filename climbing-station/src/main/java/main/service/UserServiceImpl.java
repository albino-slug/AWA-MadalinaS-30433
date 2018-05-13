package main.service;

import main.model.User;
import main.repository.UserRepository;
import main.aux.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Notification<Boolean> save(User user) {
        Notification<Boolean> saveNotification = new Notification<>();
        try {
            userRepository.save(user);
            saveNotification.setResult(Boolean.TRUE);
        }
        catch (Exception e){
            saveNotification.addError("unable to save user " + user.toString());
            saveNotification.setResult(Boolean.FALSE);
        }

        return saveNotification;
    }

    @Override
    public Notification<Boolean> deleteById(Integer id) {
        Notification<Boolean> deleteNotification = new Notification<>();
        if (id.intValue()>0){
            try{
                userRepository.deleteById(id);
                deleteNotification.setResult(Boolean.TRUE);
            }
            catch (Exception e){
                deleteNotification.setResult(Boolean.FALSE);
                deleteNotification.addError("unable to delete user with ID " + id);
            }
        }
        else{
            deleteNotification.setResult(Boolean.TRUE);
            deleteNotification.addError("user ID cannot be a negative number");
        }
        return deleteNotification;
    }
}
