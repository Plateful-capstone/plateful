package com.team5.plateful.controllers;

import com.team5.plateful.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserRepository usersDao;

    public UserController(UserRepository usersDao) {this.usersDao = usersDao;}

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "/register";
    }

}

