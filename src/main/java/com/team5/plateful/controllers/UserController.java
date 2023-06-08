package com.team5.plateful.controllers;

import com.team5.plateful.models.User;
import com.team5.plateful.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserRepository usersDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.usersDao = usersDao;
    }
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam(name="username") String username, @RequestParam(name="email") String email, @RequestParam(name="password") String password, @RequestParam(name="avatar_url") String avatar_url) {
        User user = new User(username, email, password, avatar_url);
        password = passwordEncoder.encode(password);
        usersDao.save(new User(username, email, password, avatar_url));
        return "redirect:/login";
    }

    @GetMapping("login")
    public String showLoginForm(){
        return "/login";
    }
}

