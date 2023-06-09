package com.team5.plateful.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String recipeName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String recipeDescription;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String recipeIngredients;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String recipeInstructions;

    @Column(nullable = false)
    private String recipeImageUrl;

    @Column
    private String spoonacularId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "recipe")
    private List<Cookbook> cookbooks;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "recipe")
    private List<Comment> comments;

    public Recipe(String recipeName, String recipeDescription, String recipeIngredients, String recipeInstructions, String recipeImageUrl, String spoonacularId) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
        this.recipeImageUrl = recipeImageUrl;
        this.spoonacularId = spoonacularId;
    }

    public Recipe(long id, String recipeName, String recipeDescription, String recipeIngredients, String recipeInstructions, String recipeImageUrl, User user, List<Cookbook> cookbooks, List<Comment> comments) {
        this.id = id;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
        this.recipeImageUrl = recipeImageUrl;
        this.user = user;
        this.cookbooks = cookbooks;
        this.comments = comments;
    }

    public Recipe(long id, String recipeName, String recipeDescription, String recipeIngredients, String recipeInstructions, String recipeImageUrl, String spoonacularId, User user, List<Cookbook> cookbooks, List<Comment> comments) {
        this.id = id;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
        this.recipeImageUrl = recipeImageUrl;
        this.spoonacularId = spoonacularId;
        this.user = user;
        this.cookbooks = cookbooks;
        this.comments = comments;
    }

    public Recipe(String recipeName, String recipeDescription, String recipeIngredients, String recipeInstructions, String recipeImageUrl, String spoonacularId, User user) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
        this.recipeImageUrl = recipeImageUrl;
        this.spoonacularId = spoonacularId;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe(String recipeName, String recipeDescription, String recipeIngredients, String recipeInstructions, String recipeImageUrl, User user) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
        this.recipeImageUrl = recipeImageUrl;
        this.user = user;
    }

    public Recipe(long id, String recipeName, String recipeDescription, String recipeIngredients, String recipeInstructions, String recipeImageUrl, User user) {
        this.id = id;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
        this.recipeImageUrl = recipeImageUrl;
        this.user = user;
    }

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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Recipe(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Cookbook> getCookbooks() {
        return cookbooks;
    }

    public void setCookbooks(List<Cookbook> cookbooks) {
        this.cookbooks = cookbooks;
    }

    public String getSpoonacularId() {
        return spoonacularId;
    }

    public void setSpoonacularId(String spoonacularId) {
        this.spoonacularId = spoonacularId;
    }
}