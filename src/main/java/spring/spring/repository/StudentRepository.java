package spring.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.spring.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByOrderByFirstNameAsc();
}
