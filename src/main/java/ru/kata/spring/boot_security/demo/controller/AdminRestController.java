package ru.kata.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("admin/api")
public class AdminRestController {
    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAdmin() {
        return userService.getList();
    }

    @GetMapping("user")
    public User getUser(@RequestParam("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping("roles")
    public Role[] getRoles() {
        return Role.values();
    }

    @PostMapping
    public void postAdmin(@ModelAttribute User user) {
        userService.add(user);
    }

    @PutMapping
    public void putAdmin(@ModelAttribute User user) {
        userService.update(user);
    }

    @DeleteMapping
    public void deleteAdmin(@RequestParam("id") Long id) {
        userService.delete(userService.getById(id));
    }
}
