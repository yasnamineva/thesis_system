package com.uni.thesissystem.controller;

import com.uni.thesissystem.model.User;
import com.uni.thesissystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "Username is already taken");
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/auth/login?success";
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "success", required = false) String success, Model model) {
        if (success != null) {
            model.addAttribute("success", "Registration successful! Please log in.");
        }
        return "login";
    }

    @GetMapping("/navbar")
    public String showNavbar(Authentication authentication, Model model) {
        if (authentication != null) {
            System.out.println("Authenticated user: " + authentication.getName());
            model.addAttribute("role", authentication.getAuthorities().iterator().next().getAuthority());
        }

        return "navbar";
    }

}
