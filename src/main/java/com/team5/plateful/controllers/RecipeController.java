package com.team5.plateful.controllers;

import com.team5.plateful.models.Recipe;
import com.team5.plateful.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecipeController {
    private RecipeRepository recipesDao;

    public RecipeController(RecipeRepository recipesDao) {
        this.recipesDao = recipesDao;
    }


    // Route for displaying all recipes
    @GetMapping("/recipes")
    public String recipeIndex(Model model) {
        model.addAttribute("recipes", recipesDao.findAll());
        return "recipes/index";
    }

    // Route for viewing an individual recipe
    @GetMapping("/recipes/{id}/view")
    public String viewIndividualRecipe() {
        return "recipes/show";
    }

    // Routes for creating a new recipe
    @GetMapping("/recipes/create")
    public String createRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipes/create";
    }

    @PostMapping("/recipes/create")
    public String createRecipe(@ModelAttribute Recipe recipe) {
        recipesDao.save(recipe);
        return "redirect:/recipes";
    }

    // Route for editing a recipe
    @PostMapping("/recipes/{id}/edit")
    public String editRecipeForm() {
        return "redirect:/recipes";
    }

    // Route for deleting a recipe
    @PostMapping("/recipes/{id}/delete")
    public String deleteRecipe() {
        return "redirect:/recipes";
    }
}
