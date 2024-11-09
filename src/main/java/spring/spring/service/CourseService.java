package spring.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.spring.entity.Course;
import spring.spring.repository.CourseRepository;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repository;

    @Autowired
    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> findAllCourses() {
        return repository.findAll();
    }

    public Course findCourseById(Long courseid) {
        return repository.findById(courseid).orElse(new Course());
    }

    public List<Course> findAllByOrderByNameAsc() {
        return repository.findAllByOrderByNameAsc();
    }

    public void saveCourse(Course course) {
        repository.save(course);
    }

    public void updateCourse(Course course) {
        repository.save(course);
    }

    public void deleteCourseById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllCourses() {
        repository.deleteAll();
    }

}
