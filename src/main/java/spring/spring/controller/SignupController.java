package spring.spring.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.spring.entity.User;
import spring.spring.service.UserService;

@Controller
public class SignupController {

    private final UserService userService;

    @Autowired
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("signup")
    public String signup(Model model) {
        model.addAttribute("signup", new User());
        return "signup";
    }

    @PostMapping("saveSignup")
    public String saveSignup(@Valid @ModelAttribute("signup") User user, BindingResult bindingResult, RedirectAttributes redirAttrs) {
        if (!bindingResult.hasErrors()) { // validation errors
            if (user.getPassword().equals(user.getPasswordCheck())) { // check password match
                if (userService.findByUsername(user.getUsername()) == null) { // validate username
                    // encrypt password
                    userService.encryptPassword(user);
                    user.setRole("USER");
                    userService.saveUser(user);
                    redirAttrs.addFlashAttribute("message", "User registered successfully!");
                    return "redirect:/login";
                } else {
                    bindingResult.rejectValue("username", "error.userexists", "Username already exists");
                }
            } else {
                bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Passwords does not match");
            }
        }
        return "signup";
    }

}
