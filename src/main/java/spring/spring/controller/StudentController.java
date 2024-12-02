package spring.spring.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.spring.entity.Course;
import spring.spring.entity.Student;
import spring.spring.service.CourseService;
import spring.spring.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping
    public String index(Model model) {
        List<Student> students = studentService.findAllByOrderByFirstNameAsc();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("title", "Add Student");
        return "studentForm";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") Long studentId, Model model) {
        model.addAttribute("student", studentService.findStudentById(studentId));
        model.addAttribute("title", "Edit Student");
        return "studentForm";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            String title = (student.getId() == null) ? "Add Student" : "Edit Student";
            model.addAttribute("title", title);
            return "studentForm";
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    /* @PreAuthorize("hasRole('ROLE_ADMIN')")*/
    public String deleteStudent(@PathVariable("id") Long studentId) {
        studentService.deleteStudentById(studentId);
        return "redirect:/students";
    }

    @GetMapping("/addCourse/{id}")
    public String addStudentCourse(@PathVariable("id") Long studentId, Model model) {
        model.addAttribute("student", studentService.findStudentById(studentId));
        model.addAttribute("courses", courseService.findAllCourses());
        return "addStudentCourse";
    }

    @PostMapping("/{id}/courses")
    public String studentsAddCourse(@PathVariable Long id, @RequestParam Long courseId, Model model) {
        Student student = studentService.findStudentById(id);
        Course course = courseService.findCourseById(courseId);

        if (!student.hasCourse(course)) {
            student.getCourses().add(course);
            studentService.saveStudent(student);
        }

        model.addAttribute("student", student);
        model.addAttribute("courses", courseService.findAllCourses());

        return "redirect:/students";
    }

    @GetMapping("/getstudents")
    public @ResponseBody List<Student> getStudents() {
        return studentService.findAllStudents();
    }
}
