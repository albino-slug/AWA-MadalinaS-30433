package main.controller;

import main.model.Item;
import main.model.Role;
import main.model.User;
import main.model.builder.ItemBuilder;
import main.model.builder.UserBuilder;
import main.service.AuthenticationServiceImpl;
import main.service.ItemService;
import main.service.UserService;
import main.aux.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class AdminCRUDItemsController {
    @Autowired
    ItemService itemService;

    @RequestMapping(value = "admin_items", method = RequestMethod.GET)
    public String index(Model model, HttpSession httpSession){
        if (Role.valueOf(httpSession.getAttribute("userRole").toString()) == Role.ADMIN) {
            model.addAttribute("item", new Item());
            return "admin_items";
        }
        else{
            return "redirect:/login";
        }
    }


    @RequestMapping(value = "/crud-i", method = RequestMethod.POST, params = "action=save")
    public String saveItem(@Validated @ModelAttribute("item") Item item, BindingResult bindingResult, Model model)
    {
        if (!bindingResult.hasErrors()){
            Notification<Boolean> saveNotification = itemService.save(item);
            if (saveNotification.hasErrors()){
                model.addAttribute("message", saveNotification.getFormattedErrors());
            }
            updateItemList(model);
        }
        return "admin_items";
    }
    @RequestMapping(value = "/crud-i", method = RequestMethod.POST, params = "action=delete")
    public String deleteItem(@RequestParam("id") Integer id, Model model)
    {
        Notification<Boolean> deleteNotification = itemService.deleteById(id);
        if (deleteNotification.hasErrors()){
            model.addAttribute("message", deleteNotification.getFormattedErrors());
        }
        updateItemList(model);
        model.addAttribute("item", new Item());
        return "admin_items";
    }
    @RequestMapping(value = "/crud-i", method = RequestMethod.POST, params = "action=findAll")
    public String findAll(Model model)
    {
        updateItemList(model);
        model.addAttribute("item", new Item());
        return "adminitems";
    }

    private void updateItemList(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("itemList", items);
    }
}
