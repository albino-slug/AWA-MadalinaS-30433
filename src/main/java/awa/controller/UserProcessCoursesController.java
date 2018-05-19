package awa.controller;

import awa.model.Course;
import awa.model.Mail;
import awa.model.User;
import awa.service.CourseService;
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
public class UserProcessCoursesController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "user_process_courses", method = RequestMethod.GET)
    public String courseIndex(Model model){
        model.addAttribute("course", new Course());
        return "user_process_courses";
    }

    private void updateCourseList(Model model, List<Course> courseList) {
        model.addAttribute("course", new Course());
        model.addAttribute("courseList", courseList);
    }

    @RequestMapping(value = "/user_process_courses", method = RequestMethod.POST, params = "action=listAllCourses")
    public String listAllCourses(Model model){
        updateCourseList(model, courseService.findAll());
        return "user_process_courses";
    }

    @RequestMapping(value = "/user_process_courses", method = RequestMethod.POST, params = "action=listAllCoursesOfCurrentUser")
    public String listAllOwnCourses(@RequestParam("id") Integer id, Model model, HttpSession httpSession){
        updateCourseList(model, userService.findCoursesByUserId(Integer.parseInt(httpSession.getAttribute("userId").toString())));
        return "user_process_courses";
    }

    @RequestMapping(value = "/user_process_courses", method = RequestMethod.POST, params = "action=enrollToCourse")
    public String enrollToCourse(@RequestParam("id") Integer id, Model model, HttpSession httpSession){
        User user = userService.findById(Integer.parseInt(httpSession.getAttribute("userId").toString())).get();
        courseService.addUserById(id, user.getId());
        userService.addCourseById(user.getId(), id);
        mailService.sendSimpleMessage(new Mail("awa.climbing.station@gmail.com", user.getEmail(), "Enrollment Confirmation", "You have successfully enrolled in course <<" + courseService.findById(id).get().getName() + ">>."));
        updateCourseList(model, courseService.findAll());
        return "user_process_courses";
    }

    @RequestMapping(value = "/user_process_courses", method = RequestMethod.POST, params = "action=dropOutOfCourse")
    public String dropCourse(@RequestParam("id") Integer id, Model model, HttpSession httpSession){
        User user = userService.findById(Integer.parseInt(httpSession.getAttribute("userId").toString())).get();
        courseService.removeUserById(id, user.getId());
        userService.removeCourseById(user.getId(), id);
        mailService.sendSimpleMessage(new Mail("awa.climbing.station@gmail.com", user.getEmail(), "Dropping Out Confirmation", "You have successfully dropped out of the course <<" + courseService.findById(id).get().getName() + ">>."));
        updateCourseList(model, userService.findCoursesByUserId(user.getId()));
        return "user_process_courses";
    }
}
