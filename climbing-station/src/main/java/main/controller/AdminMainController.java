package main.controller;

import main.model.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AdminMainController {

    @RequestMapping(value = "admin_view", method = RequestMethod.GET)
    public String index(HttpSession httpSession){
        if (Role.valueOf(httpSession.getAttribute("userRole").toString()) == Role.ADMIN) {
            return "admin_view";
        }
        else{
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/adminView", method = RequestMethod.POST, params = "redirect=toItemCrud")
    public String switchToItemCrud()
    {
        return"redirect:/crud_items";
    }

    @RequestMapping(value = "/adminView", method = RequestMethod.POST, params = "redirect=toUserCrud")
    public String switchToUserCrud()
    {
        return"redirect:/crud_user";
    }
}
