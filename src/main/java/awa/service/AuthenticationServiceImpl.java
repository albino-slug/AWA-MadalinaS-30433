package awa.service;

import awa.model.User;
import awa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    @Autowired
    private UserRepo userRepo;

    @Override
    public Optional<User> loadByNameAndPassword(String username, String password) {
        Optional<User> resultUser = userRepo.findByUsernameAndPassword(username, password);
        if (resultUser.isPresent()){
            return resultUser;
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public Boolean register(User user) {
        return null;
    }

}
