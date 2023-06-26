package com.team5.plateful.repositories;

import com.team5.plateful.models.Comment;
import com.team5.plateful.models.Recipe;
import com.team5.plateful.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findById(long id);
    void deleteById(long id);



    List<Comment> findAllByRecipe(Recipe recipe);

}
