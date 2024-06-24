package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.enums.StyleType;

public class PaintingDto {
    private long id;
    private String name;
    private String author;
    private String imageUrl;
    private StyleType style;
    private String username;
    private long votes;

    public long getId() {
        return id;
    }

    public PaintingDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PaintingDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public PaintingDto setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public PaintingDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public StyleType getStyle() {
        return style;
    }

    public PaintingDto setStyle(StyleType style) {
        this.style = style;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public PaintingDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public long getVotes() {
        return votes;
    }

    public PaintingDto setVotes(long votes) {
        this.votes = votes;
        return this;
    }
}
