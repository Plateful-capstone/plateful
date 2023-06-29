package com.team5.plateful.repositories;

import com.team5.plateful.models.Cookbook;
import com.team5.plateful.models.Recipe;
import com.team5.plateful.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CookbookRepository extends JpaRepository<Cookbook, Long> {
    Cookbook findByRecipeIdAndUserId(long recipeId, long userId);
}
