package com.paintingscollectors.model.dto;

import com.paintingscollectors.vallidation.annotation.UniqueEmail;
import com.paintingscollectors.vallidation.annotation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterDto {
    @NotNull
    @UniqueUsername
    @Size(min = 2, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;
    @Email(message = "Insert valid email!")
    @UniqueEmail
    @NotEmpty(message = "Email cannot be empty!")
    private String email;
    @NotNull
    @Size(min = 2, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;
    @NotNull
    @Size(min = 2, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public UserRegisterDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
