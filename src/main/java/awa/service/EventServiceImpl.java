package awa.service;

import awa.model.Event;
import awa.repository.EventRepo;
import awa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Event> findAll() {
        return eventRepo.findAll();
    }

    @Override
    public Boolean save(Event event) {
        try{
            eventRepo.save(event);
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
                eventRepo.delete(id);
            }
            catch (Exception e) {
                e.printStackTrace();
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public Optional<Event> findById(Integer id) {
        return eventRepo.findById(id);
    }

    @Override
    public List<Event> findByDate(Date date) {
        return eventRepo.findByDate(date);
    }

    @Override
    public List<Event> findByName(String name) {
        return eventRepo.findByName(name);
    }

    @Override
    public Boolean addUserById(Integer eventId, Integer userId) {
        if (eventId.intValue() < 0 || userId.intValue() < 0){
            return Boolean.FALSE;
        }
        else {
            try {
                if (eventRepo.findById(eventId).isPresent()){
                    Event event = eventRepo.findById(eventId).get();
                    event.userAttend(userRepo.findById(userId));
                    eventRepo.save(event);
                    System.out.println("User " + userId + " has been added to event " + eventId);
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
    public void removeUserById(Integer eventId, Integer userId){
        if (eventId.intValue() < 0 || userId.intValue() < 0){
            return ;
        }
        else {
            try {
                if (eventRepo.findById(eventId).isPresent()){
                    Event event = eventRepo.findById(eventId).get();
                    event.dropUser(userRepo.findById(userId));
                    eventRepo.save(event);
                    System.out.println("User " + userId + " has been removed from course " + eventId);
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
}
