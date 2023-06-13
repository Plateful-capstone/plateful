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
    @Column(nullable = false)
    private String avatar_url;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
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


    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }



    public User() {
    }

    public User(String username, String email, String password, String avatar_url) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar_url = avatar_url;
    }

    public User(long id, String username, String email, String password, String avatar_url) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar_url = avatar_url;
    }
}
