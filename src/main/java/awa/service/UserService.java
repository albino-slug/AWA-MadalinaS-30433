package awa.service;

import awa.model.Course;
import awa.model.Event;
import awa.model.Role;
import awa.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    List<Course> findCoursesByUserId(Integer id);

    public List<Event> findEventsByUserId(Integer id);

    void removeEventById(Integer userId, Integer eventId);

    void removeCourseById(Integer userId, Integer courseId);

    Boolean save(User user);

    Boolean deleteById(Integer id);

    Boolean addCourseById(Integer userId, Integer courseId);

    Boolean addEventById(Integer userId, Integer eventId);

    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String email);

    Optional<User> findByRole(Role role);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

}
