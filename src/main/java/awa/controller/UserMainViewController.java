package awa.controller;

import awa.model.Role;
import awa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserMainViewController {
    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = {"/user_main_view"}, method = RequestMethod.GET)
    public String userMainViewIndex(HttpSession httpSession) {
        if (Role.valueOf(httpSession.getAttribute("userRole").toString()) == Role.USER) {
            return "user_main_view";
        }
        else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/user_main_view", method = RequestMethod.POST, params = "redirect=toUserEvents")
    public String switchToUserCrudUsers() { return "redirect:/user_process_events"; }

    @RequestMapping(value = "/user_main_view", method = RequestMethod.POST, params = "redirect=toUserCourses")
    public String switchToUserCrudEvents() { return "redirect:/user_process_courses"; }
}
