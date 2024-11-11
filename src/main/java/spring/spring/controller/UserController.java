package spring.spring.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.spring.entity.User;
import spring.spring.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @RequestParam(value = "id", required = false) Long userId, Model model) {
        if (!bindingResult.hasErrors()) { // validation errors
            if (user.getPassword() != null) {
                userService.encryptPassword(user);
            }
            if (userId == null) { // check if new user
                if (userService.findByUsername(user.getUsername()) == null) { // validate username
                    userService.saveUser(user);
                } else {
                    bindingResult.rejectValue("username", "error.userexists", "Username already exists");
                }
            } else {
                userService.saveUser(user);
            }
        } else {
            String title = (userId == null) ? "Add User" : "Edit User";
            model.addAttribute("title", title);
            return "userForm";
        }
        return "redirect:/users";
    }

    @GetMapping
    public String index(Model model) {
        List<User> users = userService.findAllByOrderByUsernameAsc();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Add User");
        return "userForm";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long userId, Model model) {
        model.addAttribute("user", userService.findUserById(userId));
        model.addAttribute("title", "Edit User");
        return "userForm";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long userId, Model model) {
        model.addAttribute("user", userService.findUserById(userId));
        model.addAttribute("title", "Show User");
        return "userShow";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUserById(userId);
        return "redirect:/users";
    }
}
