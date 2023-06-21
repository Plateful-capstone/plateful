package com.team5.plateful.controllers;

import com.team5.plateful.models.Comment;
import com.team5.plateful.models.Recipe;
import com.team5.plateful.models.User;
import com.team5.plateful.repositories.CommentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {
    private CommentRepository commentDao;

    public CommentController(CommentRepository commentDao) {
        this.commentDao = commentDao;
    }

    @GetMapping("/comments")
    @ResponseBody
    public List<Comment> getComments() {
        return commentDao.findAll();
    }
    @GetMapping("/comments/create")
    public String createCommentForm(Model model) {
        model.addAttribute("comment", new Comment());
        return "comment/create";
    }

    @PostMapping("/comments/{id}/create")
    public String createComment(@ModelAttribute Comment comment) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setUser(user);
        System.out.println("Received Comment: " + comment.toString()); // Debugging statement
        commentDao.save(comment);
        return "redirect:/comments";
    }

    @PostMapping("/comments/{id}/delete")
    public String deleteComment(@PathVariable long id) {
        commentDao.deleteById(id);
        return "redirect:/comments";
    }

    @PostMapping("/comments/{id}/update")
    public String updateComment(@PathVariable long id, @RequestParam("body") String newComment) {
        Optional<Comment> optionalComment = Optional.ofNullable(commentDao.findById(id));
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setComment(newComment);
            commentDao.save(comment);
        }
        return "redirect:/comments";
    }
}
