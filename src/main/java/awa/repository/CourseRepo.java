package awa.repository;

import awa.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {

    Boolean deleteById(Integer id);

    Optional<Course> findById(Integer id);

    Optional<Course> findByName(String name);

    List<Course> findByStartDate(Date date);
}
