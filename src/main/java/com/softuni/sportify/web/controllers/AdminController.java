package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.services.CloudinaryService;
import com.softuni.sportify.services.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ImageService imageService;

    @Autowired
    public AdminController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/panel")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView adminPanel(ModelAndView modelAndView) {

        ImageServiceModel usersImage = this.imageService.findImageByName("users");
        ImageServiceModel imagesImage = this.imageService.findImageByName("images");
        ImageServiceModel sportCentersImage = this.imageService.findImageByName("sport-centers");
        ImageServiceModel sportsImage = this.imageService.findImageByName("sports");

        modelAndView.addObject("usersImage", usersImage.getImageURL());
        modelAndView.addObject("imagesImage", imagesImage.getImageURL());
        modelAndView.addObject("sportCentersImage", sportCentersImage.getImageURL());
        modelAndView.addObject("sportsImage", sportsImage.getImageURL());

        modelAndView.setViewName("admin/admin-panel");
        return modelAndView;
    }

}
