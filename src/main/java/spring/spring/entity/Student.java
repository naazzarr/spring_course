package spring.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Firstname is invalid")
    @Column(name = "firstname")
    private String firstName;

    @Size(min = 3, message = "Lastname is invalid")
    @Column(name = "lastname")
    private String lastName;

    @Size(min = 2, message = "Department is invalid")
    @Column(name = "department")
    private String department;

    @NotEmpty
    @Email
    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "courseid")
    )
    private Set<Course> courses = new HashSet<>(0);

    public boolean hasCourse(Course course) {
        return courses.stream().anyMatch(studentCourse ->
                studentCourse.getCourseid().equals(course.getCourseid()));
    }
}
