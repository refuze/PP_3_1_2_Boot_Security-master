package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/restController")
    public String getRest() {
        return "restAdmin";
    }

//    @PostMapping
//    public String postAdmin(@ModelAttribute User user) {
//        userService.add(user);
//        return "redirect:/admin";
//    }
//
//    @PutMapping
//    public String putAdmin(@ModelAttribute User user) {
//        userService.update(user);
//        return "redirect:/admin";
//    }
//
//    @DeleteMapping
//    public String deleteAdmin(@RequestParam("id") Long user) {
//        userService.delete(userService.getById(user));
//        return "redirect:/admin";
//    }
}
