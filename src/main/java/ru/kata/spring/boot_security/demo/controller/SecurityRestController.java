package ru.kata.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@RestController
@RequestMapping("registration/api")
public class SecurityRestController {
    private final UserService userService;

    public SecurityRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void postRegistration(@RequestBody User user) {
        userService.add(user);
    }
}
