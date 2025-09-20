package com.example.springsecurity.controller;


import com.example.springsecurity.model.Role;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String,String> register(@RequestBody RegisterRequest req) {
        Optional<User> opt = userRepository.findByUsername(req.username);
        if (opt.isPresent()) {
            return Map.of("error","username exists");
        }
        User u = new User();
        u.setUsername(req.username);
        u.setPassword(passwordEncoder.encode(req.password));
        u.setEnabled(true);
        // for simplicity, assign USER role
        Role role = new Role(); // you should fetch from DB
        // ... code to fetch role and assign
        userRepository.save(u);
        return Map.of("message","user registered");
    }

    static class RegisterRequest {
        public String username;
        public String password;
    }
}
