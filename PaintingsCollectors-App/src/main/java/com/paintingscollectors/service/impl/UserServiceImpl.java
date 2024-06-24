package com.paintingscollectors.service.impl;

import com.paintingscollectors.model.dto.PaintingDto;
import com.paintingscollectors.model.dto.UserDto;
import com.paintingscollectors.model.dto.UserLoginDto;
import com.paintingscollectors.model.dto.UserRegisterDto;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.service.UserService;
import com.paintingscollectors.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean getUniqueUsername(String username) {
        return this.userRepository.findByUsername(username).isEmpty();
    }

    @Override
    public boolean getUniqueEmail(String email) {
        return this.userRepository.findByEmail(email).isEmpty();
    }

    @Override
    public boolean isLoggedUser() {
        return this.loggedUser.isLogged();
    }

    @Override
    public User getLoggedUser() {
        return this.userRepository.findById(this.loggedUser.getId())
                .orElseThrow(() -> new RuntimeException("User is not found!"));
    }

    @Override
    public Set<PaintingDto> getAllPaintingsOfLoggedUser() {
        User user = this.getLoggedUser();
        return user.getPaintings()
                .stream()
                .map(this::mapPaintigToPaintingDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserDto> getAllPaintingsOfOthersUsers() {
        Set<User> otherUsers = this.getNotLoggedUsers(this.getLoggedUser().getId());

        return otherUsers.stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<User> getAllFavoritePaintingsOfOthersUsers() {
        return this.getNotLoggedUsers(this.getLoggedUser().getId());
    }

    @Override
    public Set<User> getNotLoggedUsers(long userid) {
        return this.userRepository.findAllByIdNot(userid);
    }

    @Override
    @Transactional
    public Set<PaintingDto> getMyFavoritesList() {
        User user = this.getLoggedUser();
        Set<Painting> favoritePaintings = user.getFavoritePaintings();
        return favoritePaintings.stream()
                .map(this::mapPaintigToPaintingDto)
                .collect(Collectors.toSet());
    }




    @Override
    public void registerUser(UserRegisterDto registerDto) {
        User user = this.modelMapper.map(registerDto, User.class);
        user.setPassword(this.passwordEncoder.encode(registerDto.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public boolean loginUser(UserLoginDto loginDto) {
        if (!this.checkCredentials(loginDto.getUsername(), loginDto.getPassword())) {
            return false;
        }
        User user = this.userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Not found Username!!!"));
        this.loggedUser.login(user);
        return true;
    }

    @Override
    public void logoutUser() {
        this.loggedUser.logout();
    }

    private UserDto mapUserToUserDto(User user) {
        List<PaintingDto> paintingList = user.getPaintings()
                .stream()
                .map(this::mapPaintigToPaintingDto)
                .toList();

        return new UserDto()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPaintingList(paintingList);
    }

    private PaintingDto mapPaintigToPaintingDto(Painting painting) {
        return new PaintingDto()
                .setId(painting.getId())
                .setName(painting.getName())
                .setAuthor(painting.getAuthor())
                .setImageUrl(painting.getImageUrl())
                .setStyle(painting.getStyle().getName())
                .setUsername(painting.getOwner().getUsername());
    }

    private boolean checkCredentials(String username, String password) {
        Optional<User> optUser = this.userRepository.findByUsername(username);
        return optUser
                .filter(user -> this.passwordEncoder.matches(password, user.getPassword()))
                .isPresent();
    }
}
