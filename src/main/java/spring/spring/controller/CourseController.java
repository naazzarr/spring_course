package spring.spring.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.spring.entity.Course;
import spring.spring.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String index(Model model) {
        List<Course> courses = courseService.findAllByOrderByNameAsc();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/new")
    public String newCourse(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("title", "Add Course");
        return "courseForm";
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable("id") Long courseId, Model model) {
        model.addAttribute("course", courseService.findCourseById(courseId));
        model.addAttribute("title", "Edit Course");
        return "courseForm";
    }

    @GetMapping("/{id}")
    public String showCourse(@PathVariable("id") Long courseId, Model model) {
        model.addAttribute("course", courseService.findCourseById(courseId));
        model.addAttribute("title", "Show Course");
        return "courseShow";
    }

    @PostMapping("/save")
    public String saveCourse(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            courseService.saveCourse(course);
            return "redirect:/courses";
        } else {
            String title = (course.getCourseid() == null) ? "Add Course" : "Edit Course";
            model.addAttribute("title", title);
            return "courseForm";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long courseId) {
        courseService.deleteCourseById(courseId);
        return "redirect:/courses";
    }
}
