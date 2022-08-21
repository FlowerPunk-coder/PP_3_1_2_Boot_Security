package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.model.enums.RoleName;
import ru.kata.spring.boot_security.demo.model.enums.Status;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", RoleName.values());
        return "users";
    }

    @GetMapping("/user")
    public String getAddPage(Model model) {
        model.addAttribute("statuses", Status.values());
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAllRoles());
        return "add";
    }

    @GetMapping("/edit")
    public String getEditPage(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("statuses", Status.values());
        model.addAttribute("roles", roleService.findAllRoles());
        return "edit";
    }

    @PostMapping("/add")
    public String addUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @PostMapping("/update")
    public String editUser(User user) {
        userService.updateUser(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/del/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/";
    }
}
