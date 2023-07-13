package com.example.ProductManagementSystemWithOAuth.controller;

import com.example.ProductManagementSystemWithOAuth.model.Role;
import com.example.ProductManagementSystemWithOAuth.model.User;
import com.example.ProductManagementSystemWithOAuth.repository.RoleRepository;
import com.example.ProductManagementSystemWithOAuth.repository.UserRepository;
import com.example.ProductManagementSystemWithOAuth.service.PasswordEncoderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class LoginController {

    private final PasswordEncoderService passwordEncoderService;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    private LoginController(UserRepository userRepository,RoleRepository roleRepository,PasswordEncoderService passwordEncoderService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoderService = passwordEncoderService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user")User user, HttpServletRequest request) throws ServletException {
        String password = user.getPassword();
        user.setPassword(passwordEncoderService.encodePassword(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).orElseThrow(() -> new RuntimeException("Role not found with ID: 2")));
        user.setRoles(roles);
        userRepository.save(user);
        try {
            request.login(user.getEmail(), password);
        } catch (ServletException e) {
            //Handling Server Exception during Register
            throw e;
        }
        return "redirect:/";
    }
}
