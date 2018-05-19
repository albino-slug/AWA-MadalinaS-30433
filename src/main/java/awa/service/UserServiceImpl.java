package awa.service;

import awa.model.Course;
import awa.model.Event;
import awa.model.Role;
import awa.model.User;
import awa.repository.CourseRepo;
import awa.repository.EventRepo;
import awa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private EventRepo eventRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public Boolean save(User user) {
        try {
            userRepo.save(user);
        }
        catch (Exception e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteById(Integer id) {
        if (id.intValue() < 0){
            return Boolean.FALSE;
        }
        else {
            try {
                userRepo.deleteById(id);
            }
            catch (Exception e) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean addCourseById(Integer userId, Integer courseId) {
        if (courseId.intValue() < 0 || userId.intValue() < 0){
            return Boolean.FALSE;
        }
        else {
            try {
                if (courseRepo.findById(courseId).isPresent()){
                    User user = userRepo.findById(userId).get();
                    user.addCourseToUser(courseRepo.findById(courseId));
                    userRepo.save(user);
                    System.out.println("Course " + courseId + " has been added to user " + userId);
                }
                else {
                    return Boolean.FALSE;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean addEventById(Integer userId, Integer eventId) {
        if (eventId.intValue() < 0 || userId.intValue() < 0){
            return Boolean.FALSE;
        }
        else {
            try {
                if (eventRepo.findById(eventId).isPresent()){
                    User user = userRepo.findById(userId).get();
                    user.addEventToUser(eventRepo.findById(eventId));
                    userRepo.save(user);
                    System.out.println("Event " + eventId + " has been added to user " + userId);
                }
                else {
                    return Boolean.FALSE;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public void removeEventById(Integer userId, Integer eventId) {
        if (eventId.intValue() < 0 || userId.intValue() < 0){
            return;
        }
        else {
            try {
                if (eventRepo.findById(eventId).isPresent()){
                    User user = userRepo.findById(userId).get();
                    user.dropEvent(eventRepo.findById(eventId));
                    userRepo.save(user);
                    System.out.println("Event " + eventId + " has been removed from user " + userId);
                }
                else {
                    return;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    @Override
    public void removeCourseById(Integer userId, Integer courseId) {
        if (courseId.intValue() < 0 || userId.intValue() < 0){
            return;
        }
        else {
            try {
                if (courseRepo.findById(courseId).isPresent()){
                    User user = userRepo.findById(userId).get();
                    user.dropCourse(courseRepo.findById(courseId));
                    userRepo.save(user);
                    System.out.println("Course " + courseId + " has been removed from user " + userId);
                }
                else {
                    return;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public Optional<User> findByRole(Role role) {
        return userRepo.findByRole(role);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<Course> findCoursesByUserId(Integer id) {
        return userRepo.findById(id).get().getCourses();
    }

    @Override
    public List<Event> findEventsByUserId(Integer id) {return userRepo.findById(id).get().getEvents();}
}
