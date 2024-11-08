package spring.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 3, message = "Username is invalid")
    @Column(name = "username", nullable = false)
    private String username;

    @Size(min = 4, message = "Password is invalid")
    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    @Column(nullable = false, updatable = false)
    private String passwordCheck;

    @Column(name = "role")
    private String role;
}
