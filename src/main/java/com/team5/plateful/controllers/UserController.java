// Import necessary dependencies
package com.team5.plateful.controllers;

import com.team5.plateful.models.Cookbook;
import com.team5.plateful.models.Recipe;
import com.team5.plateful.models.User;
import com.team5.plateful.repositories.CookbookRepository;
import com.team5.plateful.repositories.RecipeRepository;
import com.team5.plateful.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    private RecipeRepository recipesDao;

    private final UserRepository usersDao;
    private final CookbookRepository cookbooksDao;
    // Dependency injection for PasswordEncoder
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder, RecipeRepository recipesDao, CookbookRepository cookbooksDao) {
        this.passwordEncoder = passwordEncoder;
        this.usersDao = usersDao;
        this.recipesDao = recipesDao;
        this.cookbooksDao = cookbooksDao;
    }

    // handle GET request for the landing page
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("recipes", recipesDao.findAll());
        return "index";
    }

    // handle get request for the about page
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }

    @PostMapping("/login")
    public String loginSessionSetter(Model model, HttpSession session) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("user", user);
        return "redirect:/profile";
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    // Handle POST request for user registration
    @PostMapping("/register")
    public String registerUser(@RequestParam(name="username") String username, @RequestParam(name="email") String email, @RequestParam(name="password") String password, @RequestParam(name="avatarImageUrl") String avatar_url, RedirectAttributes redirectAttributes) {
        User user = new User(username, email, password, avatar_url);
//        if the user exists, let the user know it exists
        User existingUser = usersDao.findUserByUsername(username);
        if (existingUser != null) {
            redirectAttributes.addAttribute("usernameExists", true);
            return "redirect:/register";
        }
        User existingEmail = usersDao.findUserByEmail(email);
        if (existingEmail != null) {
            redirectAttributes.addAttribute("emailExists", true);
            return "redirect:/register";
        }
        password = passwordEncoder.encode(password);
        usersDao.save(new User(username, email, password, avatar_url));
        return "redirect:/login";
    }

    // Handle GET request for the user profile page
    @GetMapping("/profile")
    public String showProfile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        user = usersDao.findUserById(userId);
        model.addAttribute("user", user);
//        List<Recipe> userRecipes = user.getRecipes();
//        model.addAttribute("userRecipes", userRecipes);
        List<Cookbook> userCookbook = user.getCookbook();
        model.addAttribute("userCookbook", userCookbook);
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


    @GetMapping("/profile/update")
    public String updateProfile(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        user = usersDao.findUserById(userId);
        model.addAttribute("user", user);
        return "profile/update";
    }


    // Handle POST request for updating user profile
    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam(name="email") String email, @RequestParam(name="username") String username, @RequestParam(name="password") String password) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        user = usersDao.findUserById(userId);
        password = passwordEncoder.encode(password);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        usersDao.save(user);
        return "redirect:/profile";
    }

    // Handle GET request for the loading page
    @GetMapping("/loading")
    public String loading() {
        return "loading";
    }


    //change user image utilizing filestack API
    @PostMapping("/profile/changeImage")
    public String changeImage(@RequestParam(name="avatarImageUrl") String avatar_url){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        user = usersDao.findUserById(userId);
        System.out.println(user.getAvatar_url());
        user.setAvatar_url(avatar_url);
        System.out.println(avatar_url);
        usersDao.save(user);
        return "redirect:/profile";
    }

}
