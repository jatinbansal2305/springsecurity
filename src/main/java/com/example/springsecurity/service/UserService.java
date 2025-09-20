package com.example.springsecurity.service;

import com.example.springsecurity.model.Role;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repository.RoleRepository;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // initialize some users & roles
    @PostConstruct
    public void init() {
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_ADMIN"));
        }
        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_USER"));
        }

        if (userRepository.findByUsername("admin").isEmpty()) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();
            User u = new User();
            u.setUsername("admin");
            u.setPassword(passwordEncoder.encode("password"));
            u.setEnabled(true);
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            u.setRoles(roles);
            userRepository.save(u);
        }

        if (userRepository.findByUsername("user").isEmpty()) {
            Role userRole = roleRepository.findByName("ROLE_USER").get();
            User u = new User();
            u.setUsername("user");
            u.setPassword(passwordEncoder.encode("password"));
            u.setEnabled(true);
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            u.setRoles(roles);
            userRepository.save(u);
        }
    }
}