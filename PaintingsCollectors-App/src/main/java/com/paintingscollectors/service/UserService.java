package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.PaintingDto;
import com.paintingscollectors.model.dto.UserDto;
import com.paintingscollectors.model.dto.UserLoginDto;
import com.paintingscollectors.model.dto.UserRegisterDto;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;

import java.util.Set;

public interface UserService {
    boolean getUniqueUsername(String username);

    boolean getUniqueEmail(String email);

    void registerUser(UserRegisterDto registerDto);

    boolean loginUser(UserLoginDto loginDto);

    void logoutUser();

    boolean isLoggedUser();

    User getLoggedUser();

    Set<PaintingDto> getAllPaintingsOfLoggedUser();

    Set<UserDto> getAllPaintingsOfOthersUsers();
    Set<User> getAllFavoritePaintingsOfOthersUsers();

    Set<User> getNotLoggedUsers(long userid);

    Set<PaintingDto> getMyFavoritesList();


}
