package com.team5.plateful.controllers;

import com.team5.plateful.models.Comment;
import com.team5.plateful.models.Recipe;
import com.team5.plateful.models.User;
import com.team5.plateful.repositories.CommentRepository;
import com.team5.plateful.repositories.RecipeRepository;
import com.team5.plateful.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {
    private final CommentRepository commentsDao;
    private final RecipeRepository recipesDao;
    private final UserRepository usersDao;

    public CommentController(CommentRepository commentsDao, RecipeRepository recipesDao, UserRepository usersDao) {
        this.commentsDao = commentsDao;
        this.recipesDao = recipesDao;
        this.usersDao = usersDao;
    }
    @GetMapping("/recipes/{recipeId}/comments")
    public List<Comment> viewAllComments(@PathVariable long recipeId) {
        // Find the recipe
        Recipe recipe = recipesDao.findById(recipeId);

        // Find the comments for the recipe
        List<Comment> comments = commentsDao.findAllByRecipe(recipe);

        return comments;
    }

    @PostMapping("/recipes/{recipeId}/comments/create")
    public String addComment(@PathVariable long recipeId, @RequestParam(name = "comment-body") String commentBody) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = usersDao.findUserById(user.getId());
        Recipe recipe = recipesDao.findById(recipeId);
        Comment comment = new Comment(commentBody, user, recipe);
        commentsDao.save(comment);
        return "redirect:/recipes/" + recipeId + "/view";
    }

    @PostMapping("/recipes/{recipeId}/comments/{commentId}/delete")
    public String deleteComment(@PathVariable long recipeId, @PathVariable long commentId) {
        commentsDao.deleteById(commentId);
        return "redirect:/recipes/" + recipeId + "/view";
    }

    @PostMapping("/recipes/{recipeId}/comments/{commentId}/edit")
    public String editComment(@PathVariable long recipeId, @PathVariable long commentId, @RequestParam(name = "change-comment") String commentBody) {
        Comment comment = commentsDao.findById(commentId);
        comment.setBody(commentBody);
        commentsDao.save(comment);
        return "redirect:/recipes/" + recipeId + "/view";
    }

}
