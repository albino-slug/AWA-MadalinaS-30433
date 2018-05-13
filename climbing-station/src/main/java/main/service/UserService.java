package main.service;

import main.model.User;
import main.aux.Notification;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> findAll();
    public Optional<User> findById(Integer id);
    public Notification<Boolean> save(User user);
    public Notification<Boolean> deleteById(Integer id);
}
