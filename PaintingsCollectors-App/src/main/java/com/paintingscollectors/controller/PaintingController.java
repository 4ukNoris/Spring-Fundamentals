package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.CreatePantingDto;
import com.paintingscollectors.model.dto.PaintingDto;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.service.UserService;
import com.paintingscollectors.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/paintings")
public class PaintingController {
    private final PaintingService paintingService;
    private final UserService userService;

    @Autowired
    public PaintingController(PaintingService paintingService, UserService userService) {
        this.paintingService = paintingService;
        this.userService = userService;
    }
    @ModelAttribute("createPainting")
    public CreatePantingDto initPaintingCreate() {
        return new CreatePantingDto();
    }

    @GetMapping("/add-painting")
    public String getAddPainting() {
        if (!this.userService.isLoggedUser()) {
            return Constant.REDIRECT_LOGIN;
        }
        return "add-painting";
    }

    @PostMapping("/add-painting")
    public String postAddPainting(@Valid CreatePantingDto createPanting,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (!this.userService.isLoggedUser()) {
            return Constant.REDIRECT_LOGIN;
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute(Constant.PAINTING_ATTRIBUTE_NAME, createPanting)
                    .addFlashAttribute(Constant.PAINTING_BINDING_RESULT_NAME, bindingResult);
            return Constant.REDIRECT_PAINTING_ADD;
        }
        this.paintingService.paintingCreate(createPanting);
        return Constant.REDIRECT_HOME;
    }
    @GetMapping("/add-favorite/{id}")
    public String addFavorite(@PathVariable long id) {
        if (!this.userService.isLoggedUser()) {
            return Constant.REDIRECT_LOGIN;
        }
        Set<PaintingDto> paintingDtos = this.paintingService.addToMyFavoritesList(id);
        return Constant.REDIRECT_HOME;
    }

    @GetMapping("/remove-painting/{id}")
    public String removeFavorite(@PathVariable long id) {
        if (!this.userService.isLoggedUser()) {
            return Constant.REDIRECT_LOGIN;
        }
        this.paintingService.removePaintingFromFavorites(id);
        return Constant.REDIRECT_HOME;
    }

    @GetMapping("/vote/{id}")
    public String votePainting(@PathVariable long id) {
        if (!this.userService.isLoggedUser()) {
            return Constant.REDIRECT_LOGIN;
        }
        this.paintingService.vote(id);
        return Constant.REDIRECT_HOME;
    }
    @GetMapping("/remove/{id}")
    public String removePainting(@PathVariable long id) {
        if (!this.userService.isLoggedUser()) {
            return Constant.REDIRECT_LOGIN;
        }
        this.paintingService.remove(id);
        return Constant.REDIRECT_HOME;
    }
}
