package awa.controller;

import awa.model.Event;
import awa.model.Mail;
import awa.model.User;
import awa.service.EventService;
import awa.service.MailService;
import awa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class UserProcessEventsController {
    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "user_process_events", method = RequestMethod.GET)
    public String eventIndex(Model model){
        model.addAttribute("event", new Event());
        return "user_process_events";
    }

    private void updateEventList(Model model, List<Event> eventList) {
        model.addAttribute("event", new Event());
        model.addAttribute("eventList", eventList);
    }

    @RequestMapping(value = "/user_process_events", method = RequestMethod.POST, params = "action=listAllEvents")
    public String listAllEvents(Model model){
        updateEventList(model, eventService.findAll());
        return "user_process_events";
    }

    @RequestMapping(value = "/user_process_events", method = RequestMethod.POST, params = "action=listAllEventsOfCurrentUser")
    public String listAllOwnCourses(@RequestParam("id") Integer id, Model model, HttpSession httpSession){
        updateEventList(model, userService.findEventsByUserId(Integer.parseInt(httpSession.getAttribute("userId").toString())));
        return "user_process_events";
    }

    @RequestMapping(value = "/user_process_events", method = RequestMethod.POST, params = "action=attendEvent")
    public String attendEvent(@RequestParam("id") Integer id, Model model, HttpSession httpSession){
        User user = userService.findById(Integer.parseInt(httpSession.getAttribute("userId").toString())).get();
        eventService.addUserById(id, user.getId());
        userService.addEventById(user.getId(), id);
        mailService.sendSimpleMessage(new Mail("awa.climbing.station@gmail.com", user.getEmail(), "Confirmation for Attending an AWA Event", "You are now on the guest list for the event <<" + eventService.findById(id).get().getName() + ">>. You will also get an e-mail reminder a couple of days before the event. Happy climbing! - The AWA Team"));
        updateEventList(model, eventService.findAll());
        return "user_process_events";
    }

    @RequestMapping(value = "/user_process_events", method = RequestMethod.POST, params = "action=dropOutOfEvent")
    public String dropCourse(@RequestParam("id") Integer id, Model model, HttpSession httpSession){
        User user = userService.findById(Integer.parseInt(httpSession.getAttribute("userId").toString())).get();
        eventService.removeUserById(id, user.getId());
        userService.removeEventById(user.getId(), id);
        mailService.sendSimpleMessage(new Mail("awa.climbing.station@gmail.com", user.getEmail(), "Dropping Out Confirmation", "You have decided not to attend <<" + eventService.findById(id).get().getName() + ">> and are no longer on the waiting list. See you at any of the upcoming events. Happy climbing! - The AWA Team"));
        updateEventList(model, userService.findEventsByUserId(user.getId()));
        return "user_process_events";
    }
}
