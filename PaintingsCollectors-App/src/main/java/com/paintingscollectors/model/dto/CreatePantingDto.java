package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.model.enums.StyleType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreatePantingDto {

    @NotNull
    @Size(min = 5, max = 40, message = "Name length must be between 5 and 40 characters!")
    private String name;
    @NotNull
    @Size(min = 5, max = 30, message = "Author name must be between 5 and 30 characters!")
    private String author;
    @NotNull
    @Size(min=1, max = 150, message = "Please enter valid image URL!")
    private String imageUrl;
    @NotNull(message = "You must select a style!")
    private StyleType styleName;

    public String getName() {
        return name;
    }

    public CreatePantingDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CreatePantingDto setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CreatePantingDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public StyleType getStyleName() {
        return styleName;
    }

    public CreatePantingDto setStyleName(StyleType styleName) {
        this.styleName = styleName;
        return this;
    }
}
