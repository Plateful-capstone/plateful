package com.team5.plateful.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.plateful.models.Comment;
import com.team5.plateful.models.Cookbook;
import com.team5.plateful.models.Recipe;
import com.team5.plateful.models.User;
import com.team5.plateful.repositories.CommentRepository;
import com.team5.plateful.repositories.CookbookRepository;
import com.team5.plateful.repositories.RecipeRepository;
import com.team5.plateful.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RecipeController {
    private final RecipeRepository recipesDao;
    private final CommentRepository commentsDao;
    private final CookbookRepository cookbooksDao;
    private final UserRepository usersDao;

    public RecipeController(RecipeRepository recipesDao, CommentRepository commentsDao, CookbookRepository cookbooksDao, UserRepository usersDao) {
        this.recipesDao = recipesDao;
        this.commentsDao = commentsDao;
        this.cookbooksDao = cookbooksDao;
        this.usersDao = usersDao;
    }


//    // Route for displaying all recipes
//    @GetMapping("/recipes")
//    public String recipeIndex(Model model) {
//        model.addAttribute("recipes", recipesDao.findAll());
//        return "index_archive";
//    }

    // Route for viewing an individual recipe
    @GetMapping("/recipes/{id}/view")
    public String viewIndividualRecipe(@PathVariable long id, Model model) {
        Recipe recipe = recipesDao.findById(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = usersDao.findUserById(user.getId());
        Cookbook cookbookRecipe = cookbooksDao.findByRecipeIdAndUserId(recipe.getId(), user.getId());
        if (cookbookRecipe != null) {
            model.addAttribute("cookbook", cookbookRecipe);
        }

        if (recipe == null) {
            // Recipe not found, handle the case accordingly
            // For example, you can redirect to an error page or display a message
            return "redirect:/error";
        }
        List<Comment> comments = commentsDao.findAllByRecipe(recipe);
        model.addAttribute("comments", comments);
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recipe.setUser(user);
        System.out.println("Received Recipe: " + recipe.toString()); // Debugging statement
        recipesDao.save(recipe);
        Cookbook cookbook = new Cookbook(user, recipe);
        cookbooksDao.save(cookbook);
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
        Recipe recipe = recipesDao.findById(id);
        List<Comment> comments = commentsDao.findAllByRecipe(recipe);
        for (Comment comment : comments) {
            commentsDao.deleteById(comment.getId());
        }
        recipesDao.deleteById(id);
        return "redirect:/recipes";
    }

    // get mapping for search
    @GetMapping("/recipes")
    public String searchRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("recipes", recipesDao.findAll());
        return "recipes/index";
    }

    @GetMapping("api/recipes/search")
    @ResponseBody
    public List<Recipe> searchRecipeFromDb(@RequestParam(name = "search") String search, Model model) {
        List<Recipe> searchResults = recipesDao.findAllByRecipeNameContaining(search);
        System.out.println("Retrieved data from the database: " + searchResults);
        return searchResults;
    }


    @PostMapping("/recipes/search/create")
    @ResponseBody
    public Recipe addFromRecipeAPI(@RequestBody Recipe recipe) throws JsonProcessingException {
        System.out.println("Received Recipe: ");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(recipe));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recipe.setUser(user);
        Cookbook cookbook = new Cookbook(user, recipe);
        // Check if any matching recipes already exist
        List<Recipe> existingRecipes = recipesDao.findAllByRecipeName(recipe.getRecipeName());
        if (!existingRecipes.isEmpty()) {
            // Handle the presence of duplicate recipes as desired
            return existingRecipes.get(0); // Return the first matching recipe
        }
        recipesDao.save(recipe);
        cookbooksDao.save(cookbook);
        return recipe;
    }

}

