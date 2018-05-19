package awa.controller;
import awa.model.Role;
import awa.model.User;
import awa.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class LoginController {

    private HashMap<Role, String> redirectionByRole = new HashMap<Role, String>();

    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String LoginIndex() {
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST, params = "action=login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession httpSession) {
        redirectionByRole.put(Role.USER, "/user_main_view");
        redirectionByRole.put(Role.ADMIN, "/admin_main_view");

        Optional<User> user = authenticationService.loadByNameAndPassword(username, password);
        System.out.println("found username = " + username + " and password = " + password);
        if (user.isPresent()){

            httpSession.setAttribute("userRole", user.get().getRole());
            httpSession.setAttribute("userId", user.get().getId());
            System.out.println("redirect:" + redirectionByRole.get(user.get().getRole()));
            return "redirect:" + redirectionByRole.get(user.get().getRole());
        }
        else{
            System.out.println("redirect:/login?error");
            return "redirect:/login?error";
        }
    }
}