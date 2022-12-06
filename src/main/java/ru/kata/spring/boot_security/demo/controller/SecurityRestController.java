package ru.kata.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Collections;
import java.util.Set;

@RestController
public class SecurityRestController {
    private final UserService userService;

    public SecurityRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration/rest")
    public void postRegistration(@ModelAttribute User user) {
        if (user.getUsername().equals("admin")) {
            user.setAuthorities(Set.of(Role.USER, Role.ADMIN));
        } else {
            user.setAuthorities(Collections.singleton(Role.USER));
        }

        userService.add(user);
    }
}
