package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAdmin(ModelMap model) {
        model.addAttribute("users", userService.getList());
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", Role.values());
        return "admin";
    }

    @PostMapping
    public String saveUser(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping
    public String deleteUser(@RequestParam("id") Long user) {
        userService.delete(userService.getById(user));
        return "redirect:/admin";
    }
}
