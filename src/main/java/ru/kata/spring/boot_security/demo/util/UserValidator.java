package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.User;
@Component
public class UserValidator  implements Validator {

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserValidator(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            userDetailsService.loadUserByUsername(user.getUsername());
            errors.rejectValue("username", "", "User already exists");
        } catch (UsernameNotFoundException ignored) {
        }

        if (user.getUsername().isEmpty()) {
            errors.rejectValue("username", "", "Username is required");
        }

        if (user.getPassword().isEmpty()) {
            errors.rejectValue("password", "", "Password is required");
        }
    }
}