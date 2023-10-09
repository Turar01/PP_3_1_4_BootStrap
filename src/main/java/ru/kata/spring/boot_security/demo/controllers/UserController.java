package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    private final UserValidator userValidator;
    private final UserService userService;
    private final RoleRepository roleRepository;



    @Autowired
    public UserController(UserValidator userValidator, UserService userService, RoleRepository roleRepository) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        List<Role> allRoles = roleRepository.findAll();
        model.addAttribute("allRoles", allRoles);
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult,
                               @RequestParam(value = "selectedRoles", required = false) List<String> selectedRoles) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (selectedRoles != null && !selectedRoles.isEmpty()) {
            Set<Role> userRoles = new HashSet<>();
            for (String roleName : selectedRoles) {
                Role role = roleRepository.findByName(roleName);
                if (role != null) {
                    userRoles.add(role);
                }
            }
            user.setRoles(userRoles);
        }

        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String getUserProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("user", userService.loadUserByUsername(username));
        return "user";
    }
}