package com.team5.plateful.controllers;

import com.team5.plateful.models.User;
import com.team5.plateful.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserRepository usersDao;

    public UserController(UserRepository usersDao) {this.usersDao = usersDao;}

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam(name="username") String username, @RequestParam(name="email") String email, @RequestParam(name="password") String password, @RequestParam(name="avatar_url") String avatar_url) {
        User user = new User(username, email, password, avatar_url);
        usersDao.save(user);
        return "redirect:/login";
    }
}

