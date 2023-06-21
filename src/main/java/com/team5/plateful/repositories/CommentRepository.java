package com.team5.plateful.repositories;

import com.team5.plateful.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findById(long id);
    void deleteById(long id);

    List<Comment> findAllByBody(String body);


}
