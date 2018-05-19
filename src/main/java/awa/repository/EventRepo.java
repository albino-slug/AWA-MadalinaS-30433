package awa.repository;

import awa.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepo extends JpaRepository<Event, Integer> {

    List<Event> findAll();

//  void deleteById(Integer id);

    Optional<Event> findById(Integer id);

    List<Event> findByDate(Date date);

    List<Event> findByName(String name);
}
