package com.team5.plateful.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length=50)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

//    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
//    private List<Recipe> recipes;
//
//    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
//    private List<Comment> comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//
//    public List<Recipe> getRecipes() {
//        return recipes;
//    }
//
//    public void getRecipes(List<Recipe> posts) {
//        this.recipes = posts;
//    }
//
//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }

    public User() {
    }
}
