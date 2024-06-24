package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.PaintingDto;
import com.paintingscollectors.model.dto.UserDto;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.service.UserService;
import com.paintingscollectors.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
    private final UserService userService;
    private final PaintingService paintingService;

    @Autowired
    public HomeController(UserService userService, PaintingService paintingService) {
        this.userService = userService;
        this.paintingService = paintingService;
    }

    @GetMapping("/")
    public String index() {
        if (this.userService.isLoggedUser()) {
            return Constant.REDIRECT_HOME;
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!this.userService.isLoggedUser()) {
            return Constant.REDIRECT_LOGIN;
        }
        Set<PaintingDto> userAllPaintings = this.userService.getAllPaintingsOfLoggedUser();
        model.addAttribute("userPaintings", userAllPaintings);

        Set<UserDto> otherUsers = this.userService.getAllPaintingsOfOthersUsers();
        model.addAttribute("otherUsersPaintings", otherUsers);

        Set<PaintingDto> myFavoriteList = this.userService.getMyFavoritesList();
        model.addAttribute("myFavorites", myFavoriteList);

        List<PaintingDto> allVotedPaintings = this.paintingService.getAllPaintingsByRate();
        model.addAttribute("allVotedPaintings", allVotedPaintings);
        return "home";
    }

}
