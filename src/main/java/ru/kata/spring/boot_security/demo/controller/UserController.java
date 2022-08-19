package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/user")
    public String getUserPage(Model model, Authentication authentication) {
        model.addAttribute("user", userRepository.findByUsername(authentication.getName()));
        return "user";
    }
    @GetMapping("/logout")
    public String logout() {
        return "index";
    }
}
