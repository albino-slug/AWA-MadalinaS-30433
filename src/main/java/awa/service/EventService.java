package awa.service;

import awa.model.Event;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> findAll();

    Boolean save(Event event);

    Boolean deleteById(Integer id);

    Optional<Event> findById(Integer id);

    List<Event> findByDate(Date date);

    List<Event> findByName(String name);

    Boolean addUserById(Integer eventId, Integer userId);

    void removeUserById(Integer eventId, Integer userId);
}
