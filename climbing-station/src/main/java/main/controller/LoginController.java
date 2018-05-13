package main.controller;

import com.google.common.collect.ImmutableMap;
import main.model.Role;
import main.model.User;
import main.service.AuthenticationService;
import main.aux.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    private static final Map<Role, String> roleToViewMap = ImmutableMap.of(
            Role.ADMIN, "/admin_view",
            Role.USER, "/user_view"
    );

    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    @Order(value = 1)
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, params = "action=login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession httpSession) {
        Notification<User> userNotification = authenticationService.loadByNameAndPassword(username, password);
        if (userNotification.hasErrors()){
            System.out.println("[LoginController]" + userNotification.getFormattedErrors());
            return "redirect:/login?error";
        }
        else{
            httpSession.setAttribute("userRole", userNotification.getResult().getRole());
            return "redirect:" + roleToViewMap.get(userNotification.getResult().getRole());
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout (HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/login?logout";
    }
}