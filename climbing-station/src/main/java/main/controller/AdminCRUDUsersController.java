package main.controller;

import main.aux.Notification;
import main.model.User;
import main.service.AuthenticationService;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminCRUDUsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "crud_user", method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("indexed");
        model.addAttribute("user", new User());
        return "crud_user";
    }

    @RequestMapping(value = "/crud-u", method = RequestMethod.POST, params = "action=save")
    public String saveUser(@Validated @ModelAttribute("user") User user, BindingResult bindingResult, Model model)
    {
        if (!bindingResult.hasErrors()){
            Notification<Boolean> saveNotification = authenticationService.register(user);
            if (saveNotification.hasErrors()){
                model.addAttribute("message", saveNotification.getFormattedErrors());
            }
            updateUsersList(model);
        }
        return "crud_user";
    }
    @RequestMapping(value = "/crud-u", method = RequestMethod.POST, params = "action=delete")
    public String deleteUser(@RequestParam("id") Integer id, Model model)
    {
        Notification<Boolean> deleteNotification = userService.deleteById(id);
        if (deleteNotification.hasErrors()){
            model.addAttribute("message", deleteNotification.getFormattedErrors());
        }
        updateUsersList(model);
        model.addAttribute("user", new User());
        return "crud_user";
    }
    @RequestMapping(value = "/crud-u", method = RequestMethod.POST, params = "action=findAll")
    public String findAll(Model model)
    {
        updateUsersList(model);
        model.addAttribute("user", new User());
        return "crud_user";
    }

    private void updateUsersList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("userList", users);
    }

}
