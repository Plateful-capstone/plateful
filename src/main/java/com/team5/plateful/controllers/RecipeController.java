package com.team5.plateful.controllers;

import com.team5.plateful.models.Recipe;
import com.team5.plateful.models.User;
import com.team5.plateful.repositories.RecipeRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String viewIndividualRecipe(@PathVariable long id, Model model) {
        if (recipesDao.findById(id) == null) {
            // make API call to spoonacular
            // save recipe to database
            // return recipe

            Recipe recipe = new Recipe( );
            recipe.setRecipeName(recipe.getRecipeName());
            recipe.setRecipeDescription(recipe.getRecipeDescription());
            recipe.setRecipeIngredients(recipe.getRecipeIngredients());
            recipe.setRecipeInstructions(recipe.getRecipeInstructions());
            recipe.setRecipeImageUrl(recipe.getRecipeImageUrl());
            recipesDao.save(recipe);
            return "redirect:/recipes/{id}";
        }
        Recipe recipe = recipesDao.findById(id);
        model.addAttribute("recipe", recipe);
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
        // Get the current authenticated user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Set the user on the recipe
        recipe.setUser(user);

        // Save the recipe to the database
        recipesDao.save(recipe);

        return "redirect:/recipes";
    }


    // Route for editing a recipe
    @PostMapping("/recipes/{id}/edit")
    public String editRecipeForm(@PathVariable long id, @RequestParam(name = "recipeName") String recipe_name, @RequestParam(name = "recipeDescription") String recipe_description, @RequestParam(name = "recipeIngredients") String recipe_ingredients, @RequestParam(name = "recipeInstructions") String recipe_instructions, @RequestParam(name = "recipeImageUrl") String recipe_image) {
        Recipe recipe = recipesDao.findById(id);

        if (recipe != null) {
            recipe.setRecipeName(recipe_name);
            recipe.setRecipeDescription(recipe_description);
            recipe.setRecipeIngredients(recipe_ingredients);
            recipe.setRecipeInstructions(recipe_instructions);
            recipe.setRecipeImageUrl(recipe_image);
            recipesDao.save(recipe);
        }

        return "redirect:/recipes";
    }

    // Route for deleting a recipe
    @PostMapping("/recipes/{id}/delete")
    public String deleteRecipe(@PathVariable long id) {
        recipesDao.deleteById(id);
        return "redirect:/recipes";
    }

    // get mapping for search
    @GetMapping("/recipes/search")
    public String searchRecipeForm(Model model) {
        model.addAttribute("recipes", recipesDao.findAll());
        return "recipes/search";
    }

    // Route for searching for a recipe
    @PostMapping("/recipes/search")
    public String searchRecipe(@RequestParam(name = "search") String search, Model model) {
        model.addAttribute("recipes", recipesDao.findByRecipeNameContaining(search));
        return "recipes/search";
    }
}
