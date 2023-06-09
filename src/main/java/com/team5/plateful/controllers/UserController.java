// Import necessary dependencies
package com.team5.plateful.controllers;

import com.team5.plateful.models.Recipe;
import com.team5.plateful.models.User;
import com.team5.plateful.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserRepository usersDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder){
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
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

    @GetMapping("/login")
    public String showLoginForm(){
        return "/login";
    }

    @PostMapping("/login")
    public String loginSessionSetter(Model model, HttpSession session) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("user", user);
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "/register";
    }

    // Handle POST request for user registration
    @PostMapping("/register")
    public String registerUser(@RequestParam(name="username") String username, @RequestParam(name="email") String email, @RequestParam(name="password") String password, @RequestParam(name="avatar_url") String avatar_url) {
        User user = new User(username, email, password, avatar_url);
        password = passwordEncoder.encode(password);
        usersDao.save(new User(username, email, password, avatar_url));
        return "redirect:/login";
    }

    // Handle GET request for the user profile page
    @GetMapping("/profile")
    public String showProfile(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        user = usersDao.findUserById(userId);
        model.addAttribute("user", user);
        System.out.println(user.getUsername());
        return "profile";
    }

    // Handle POST request for updating user profile
    @PostMapping("/profile")
    public String changeProfile(@RequestParam(name="email") String email){
        System.out.println("Post mapping hit");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        user = usersDao.findUserById(userId);
        user.setEmail(email);
        usersDao.save(user);
        return "redirect:/profile";
    }
  
    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }


    @GetMapping("/profile/update")
    public String updateProfile(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        user = usersDao.findUserById(userId);
        model.addAttribute("user", user);
        return "profile/update";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam(name="email") String email, @RequestParam(name="username") String username, @RequestParam(name="password") String password, @RequestParam(name="avatar_url") String avatar_url) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        user = usersDao.findUserById(userId);
        password = passwordEncoder.encode(password);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setAvatar_url(avatar_url);
        usersDao.save(user);
        return "redirect:/profile";
    }

}
