// Import necessary dependencies
package com.team5.plateful.controllers;

import com.team5.plateful.models.User;
import com.team5.plateful.repositories.UserRepository;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    // Dependency injection for UserRepository and PasswordEncoder
    private final UserRepository usersDao;
    // Dependency injection for PasswordEncoder
//    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository usersDao){
//        this.passwordEncoder = passwordEncoder;
        this.usersDao = usersDao;
    }

    // handle GET request for the landing page
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // handle get request for the about page
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    // Handle GET request for the login form
    @GetMapping("login")
    public String showLoginForm(){
        return "/login";
    }

    // Handle GET request for the registration form
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "/register";
    }

    // Handle POST request for user registration
    @PostMapping("/register")
    public String registerUser(@RequestParam(name="username") String username, @RequestParam(name="email") String email, @RequestParam(name="password") String password, @RequestParam(name="avatar_url") String avatar_url) {
        User user = new User(username, email, password, avatar_url);
//        password = passwordEncoder.encode(password);
        usersDao.save(new User(username, email, password, avatar_url));
        return "redirect:/login";
    }

    // Handle GET request for the user profile page
    @GetMapping("/profile")
    public String showProfile(Model model){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        long userId = user.getId();
//        user = usersDao.findUserById(userId);
//        model.addAttribute("user", user);
//        // Print the username to the console (debugging purpose)
//        System.out.println(user.getUsername());
        return "profile";
    }

    // Handle POST request for updating user profile
    @PostMapping("/profile")
    public String changeProfile(@RequestParam(name="email") String email){
//        System.out.println("Post mapping hit");
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        long userId = user.getId();
//        user = usersDao.findUserById(userId);
//        user.setEmail(email);
//        usersDao.save(user);
        return "redirect:/profile";
    }
}
