package spring.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.spring.entity.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByOrderByNameAsc();
}
