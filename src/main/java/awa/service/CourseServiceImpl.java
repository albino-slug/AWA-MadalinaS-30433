package awa.service;

import awa.model.Course;
import awa.model.User;
import awa.repository.CourseRepo;
import awa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public Boolean save(Course course) {
        try{
            courseRepo.save(course);
        }
        catch (Exception e){
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
                courseRepo.delete(id);
            }
            catch (Exception e) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean addUserById(Integer courseId, Integer userId) {
        if (courseId.intValue() < 0 || userId.intValue() < 0){
            return Boolean.FALSE;
        }
        else {
            try {
                if (courseRepo.findById(courseId).isPresent()){
                    Course course = courseRepo.findById(courseId).get();
                    course.enrollUser(userRepo.findById(userId));
                    courseRepo.save(course);
                    System.out.println("User " + userId + " has been added to course " + courseId);
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
    public void removeUserById(Integer courseId, Integer userId){
        if (courseId.intValue() < 0 || userId.intValue() < 0){
            return ;
        }
        else {
            try {
                if (courseRepo.findById(courseId).isPresent()){
                    Course course = courseRepo.findById(courseId).get();
                    course.dropUser(userRepo.findById(userId));
                    courseRepo.save(course);
                    System.out.println("User " + userId + " has been removed from course " + courseId);
                }
                else {
                    return ;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                return ;
            }
        }
    }

    @Override
    public Optional<Course> findById(Integer id) {
        return courseRepo.findById(id);
    }

    @Override
    public List<Course> findByStartDate(Date date) {
        return courseRepo.findByStartDate(date);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return courseRepo.findByName(name);
    }
}
