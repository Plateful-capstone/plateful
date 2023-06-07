package com.team5.plateful.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String recipeName;

    @Column(nullable = false)
    private String recipeDescription;

    @Column(nullable = false)
    private String recipeIngredients;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String recipeInstructions;

    @Column(nullable = false)
    private String recipeImageUrl;

    public Recipe() {
    }

    public Recipe(long id, String recipeName, String recipeDescription, String recipeIngredients, String recipeInstructions, String recipeImageUrl) {
        this.id = id;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
        this.recipeImageUrl = recipeImageUrl;
    }

    public Recipe(String recipeName, String recipeDescription, String recipeIngredients, String recipeInstructions, String recipeImageUrl) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
        this.recipeImageUrl = recipeImageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstructions(String recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    public String getRecipeImageUrl() {
        return recipeImageUrl;
    }

    public void setRecipeImageUrl(String recipeImageUrl) {
        this.recipeImageUrl = recipeImageUrl;
    }
}
