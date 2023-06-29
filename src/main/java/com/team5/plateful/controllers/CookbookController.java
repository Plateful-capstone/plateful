package com.team5.plateful.controllers;

import com.team5.plateful.models.Cookbook;
import com.team5.plateful.models.Recipe;
import com.team5.plateful.models.User;
import com.team5.plateful.repositories.CommentRepository;
import com.team5.plateful.repositories.CookbookRepository;
import com.team5.plateful.repositories.RecipeRepository;
import com.team5.plateful.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CookbookController {
    private final RecipeRepository recipesDao;
    private final UserRepository usersDao;
    private final CookbookRepository cookbooksDao;

    public CookbookController(RecipeRepository recipesDao, UserRepository usersDao, CookbookRepository cookbooksDao) {
        this.recipesDao = recipesDao;
        this.usersDao = usersDao;
        this.cookbooksDao = cookbooksDao;
    }

    @PostMapping("/recipes/{recipeId}/add-to-cookbook")
    public String addRecipeToCookbook(@PathVariable long recipeId) {
        Recipe recipe = recipesDao.findById(recipeId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = usersDao.findUserById(user.getId());
        Cookbook cookbook = new Cookbook(user, recipe);
        cookbooksDao.save(cookbook);

        return "redirect:/profile";
    }
    @PostMapping("/recipes/{recipeId}/remove-from-cookbook")
    public String removeRecipeFromCookbook(@PathVariable long recipeId) {
        Recipe recipe = recipesDao.findById(recipeId);
        System.out.println("Recipe id: + " + recipe.getId());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = usersDao.findUserById(user.getId());
        System.out.println("User id: + " + user.getId());
        Cookbook cookbook = cookbooksDao.findByRecipeIdAndUserId(recipe.getId(), user.getId());
        System.out.println("Cookbook id: + " + cookbook.getId());
        cookbooksDao.delete(cookbook);

        return "redirect:/profile";
    }
}
