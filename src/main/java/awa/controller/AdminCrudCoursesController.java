package awa.controller;

import awa.model.Course;
import awa.model.Role;
import awa.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminCrudCoursesController {
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "admin_crud_courses", method = RequestMethod.GET)
    public String courseIndex(Model model, HttpSession httpSession) {
        model.addAttribute("course", new Course());
        if (Role.valueOf(httpSession.getAttribute("userRole").toString()) == Role.ADMIN) {
            return "admin_crud_courses";        }
        else{
            return "redirect:/login";
        }
    }

    private void updateCourseList(Model model, List<Course> courseList) {
        model.addAttribute("course", new Course());
        model.addAttribute("courseList", courseList);
    }

    @RequestMapping(value = "/admin_crud_courses", method = RequestMethod.POST, params = "action=addCourse")
    public String addNewCourse(@Validated @ModelAttribute("course") Course course, BindingResult bindingResult, Model model)
    {
        if (!bindingResult.hasErrors()){
            Boolean saveResult = courseService.save(course);
            if (saveResult == Boolean.FALSE){
                model.addAttribute("message", "Error while saving course");
            }
            updateCourseList(model, courseService.findAll());
        }
        return "admin_crud_courses";
    }

    @RequestMapping(value = "/admin_crud_courses", method = RequestMethod.POST, params = "action=deleteCourse")
    public String deleteCourse(@RequestParam("id") Integer id, Model model){
        Boolean deleteResult = courseService.deleteById(id);
        if (deleteResult == Boolean.FALSE){
            model.addAttribute("message", "Error while deleting course");
        }
        updateCourseList(model, courseService.findAll());
        return "admin_crud_courses";
    }

    @RequestMapping(value = "/admin_crud_courses", method = RequestMethod.POST, params = "action=listAllCourses")
    public String listAllCourses(Model model){
        updateCourseList(model, courseService.findAll());
        return "admin_crud_courses";
    }
}
