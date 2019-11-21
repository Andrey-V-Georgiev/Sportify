package com.softuni.sportify.web.controllers;

import com.softuni.sportify.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.softuni.sportify.constants.AdminControllerConstants.*;
import static com.softuni.sportify.constants.AuthConstants.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ImageService imageService;

    @Autowired
    public AdminController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/panel")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView adminPanel(ModelAndView modelAndView) {

        modelAndView.addObject("usersImage", ADMIN_PANEL_USERS_IMAGE);
        modelAndView.addObject("imagesImage", ADMIN_PANEL_IMAGES_IMAGE);
        modelAndView.addObject("sportCentersImage", ADMIN_PANEL_SPORT_CENTERS_IMAGE);
        modelAndView.addObject("sportsImage", ADMIN_PANEL_SPORT_IMAGE);

        modelAndView.setViewName(VIEW_ADMIN_PANEL);
        return modelAndView;
    }

}
