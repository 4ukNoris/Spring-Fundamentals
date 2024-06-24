package com.paintingscollectors.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Painting> paintings;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Painting> favoritePaintings;
    @ManyToMany
    private Set<Painting> ratedPaintings;


    public User() {
        this.paintings = new HashSet<>();
        this.favoritePaintings = new HashSet<>();
        this.ratedPaintings = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<Painting> getPaintings() {
        return paintings;
    }

    public User setPaintings(Set<Painting> paintings) {
        this.paintings = paintings;
        return this;
    }

    public Set<Painting> getFavoritePaintings() {
        return favoritePaintings;
    }

    public User setFavoritePaintings(Set<Painting> favoritePaintings) {
        this.favoritePaintings = favoritePaintings;
        return this;
    }

    public Set<Painting> getRatedPaintings() {
        return ratedPaintings;
    }

    public User setRatedPaintings(Set<Painting> ratedPaintings) {
        this.ratedPaintings = ratedPaintings;
        return this;
    }
}
