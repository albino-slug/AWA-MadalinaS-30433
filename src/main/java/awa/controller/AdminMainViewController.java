package awa.controller;

import awa.model.Role;
import awa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AdminMainViewController {
    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = {"/admin_main_view"}, method = RequestMethod.GET)
    public String adminMainViewIndex(HttpSession httpSession) {
        if (Role.valueOf(httpSession.getAttribute("userRole").toString()) == Role.ADMIN) {
            return "admin_main_view";
        }
        else{
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/admin_main_view", method = RequestMethod.POST, params = "redirect=toAdminCrudUsers")
    public String switchToAdminCrudUsers()
    {
        return "redirect:/admin_crud_users";
    }

    @RequestMapping(value = "/admin_main_view", method = RequestMethod.POST, params = "redirect=toAdminCrudEvents")
    public String switchToAdminCrudEvents()
    {
        return "redirect:/admin_crud_events";
    }

    @RequestMapping(value = "/admin_main_view", method = RequestMethod.POST, params = "redirect=toAdminCrudCourses")
    public String switchToAdminCrudCourses()
    {
        return "redirect:/admin_crud_courses";
    }
}
