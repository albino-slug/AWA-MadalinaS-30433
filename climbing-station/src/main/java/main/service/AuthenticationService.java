package main.service;

import main.model.User;
import main.aux.Notification;

public interface AuthenticationService {
    public Notification<User> loadByNameAndPassword(String username, String password);
    Notification<Boolean> register(User user);
}
