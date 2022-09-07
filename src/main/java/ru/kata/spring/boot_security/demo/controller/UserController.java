package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class UserController {

    private final UserDetailsService userDetailsService;

    public UserController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @GetMapping("/user")
    public String getUserPage(Model model, Authentication authentication) {
        model.addAttribute("user", userDetailsService.loadUserByUsername(authentication.getName()));
        return "user";
    }
    @GetMapping("/logout")
    public String logout() {
        return "index";
    }
}
