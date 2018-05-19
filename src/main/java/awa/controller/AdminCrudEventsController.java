package awa.controller;

import awa.model.Course;
import awa.model.Event;
import awa.model.Role;
import awa.model.User;
import awa.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminCrudEventsController {
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "admin_crud_events", method = RequestMethod.GET)
    public String eventIndex(Model model, HttpSession httpSession) {
        model.addAttribute("event", new Event());
        if (Role.valueOf(httpSession.getAttribute("userRole").toString()) == Role.ADMIN) {
            return "admin_crud_events";        }
        else{
            return "redirect:/login";
        }
    }

    private void updateEventList(Model model, List<Event> eventList) {
        model.addAttribute("event", new Event());
        model.addAttribute("eventList", eventList);
    }

    @RequestMapping(value = "/admin_crud_events", method = RequestMethod.POST, params = "action=addEvent")
    public String addNewEvent(@Validated @ModelAttribute("event") Event event, BindingResult bindingResult, Model model)
    {
        if (!bindingResult.hasErrors()){
            Boolean saveResult = eventService.save(event);
            if (saveResult == Boolean.FALSE){
                model.addAttribute("message", "Error while saving event");
            }
            updateEventList(model, eventService.findAll());
        }
        return "admin_crud_events";
    }

    @RequestMapping(value = "/admin_crud_events", method = RequestMethod.POST, params = "action=deleteEvent")
    public String deleteEvent(@RequestParam("id") Integer id, Model model){
        Boolean deleteResult = eventService.deleteById(id);
        if (deleteResult == Boolean.FALSE){
            model.addAttribute("message", "Error while deleting event");
        }
        updateEventList(model, eventService.findAll());
        return "admin_crud_events";
    }

    @RequestMapping(value = "/admin_crud_events", method = RequestMethod.POST, params = "action=listAllEvents")
    public String listAllEvents(Model model){
        updateEventList(model, eventService.findAll());
        return "admin_crud_events";
    }
}
