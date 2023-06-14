package com.team5.plateful.repositories;

import com.team5.plateful.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findById(long id);
    void deleteById(long id);
//    Recipe findByRecipeNameContaining(String recipeName);
    List<Recipe> findAllByRecipeNameContaining(String recipeName);
    List<Recipe> findAll();

    Recipe findByRecipeName(String recipeName);

    List<Recipe> findAllByRecipeName(String recipeName);
}
