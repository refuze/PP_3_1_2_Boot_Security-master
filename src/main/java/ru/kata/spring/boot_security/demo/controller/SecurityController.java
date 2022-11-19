package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Collections;
import java.util.Set;

@Controller
public class SecurityController {
    private UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @Controller
    public class LoginPageController {

        @GetMapping("/login")
        public String login(){
            return "security/login";
        }
    }

    @GetMapping("/registration")
    public String getRegistration() {
        return "security/registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@ModelAttribute User user, ModelMap model) {

        if (!userService.getByUsername(user.getUsername()).isEmpty()) {
            model.addAttribute("error", "Username already reserved.");
            return "security/registration";
        }

        if (user.getUsername().equals("admin")) {
            user.setAuthorities(Set.of(Role.USER, Role.ADMIN));
        } else {
            user.setAuthorities(Collections.singleton(Role.USER));
        }

        user.setEnabled(true);

        userService.add(user);

        return "redirect:/login";
    }
}
