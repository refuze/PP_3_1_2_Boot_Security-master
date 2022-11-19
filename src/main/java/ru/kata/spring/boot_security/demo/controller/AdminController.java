package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String usersList(ModelMap model) {
        model.addAttribute("users", userService.getList());
        return "users";
    }

    @GetMapping("/user-add")
    public String userAdd(ModelMap model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/user-add")
    public String saveUser(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/user-update/{user}")
    public String updateUser(ModelMap model, @PathVariable Long user) {
        model.addAttribute("user", userService.getById(user));
        model.addAttribute("roles", Role.values());
        return "user-form";
    }

    @DeleteMapping("/user-delete/{user}")
    public String deleteUser(@PathVariable Long user) {
        userService.delete(userService.getById(user));
        return "redirect:/admin/users";
    }
}
