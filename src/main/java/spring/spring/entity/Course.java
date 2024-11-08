package spring.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseid")
    private Long courseid;

    @Size(min = 3, message = "Course is invalid")
    @Column(name = "coursename")
    private String name;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
}
