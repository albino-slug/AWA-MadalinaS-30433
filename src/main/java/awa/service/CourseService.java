package awa.service;

import awa.model.Course;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> findAll();

    Boolean save(Course course);

    Boolean deleteById(Integer id);

    Boolean addUserById(Integer courseId, Integer userId);

    void removeUserById(Integer courseId, Integer userId);

    Optional<Course> findById(Integer id);

    List<Course> findByStartDate(Date date);

    Optional<Course> findByName(String name);
}
