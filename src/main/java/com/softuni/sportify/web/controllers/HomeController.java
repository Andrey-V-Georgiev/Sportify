package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.services.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final ModelMapper modelMapper;
    private final ImageService imageService;

    @Autowired
    public HomeController(ModelMapper modelMapper, ImageService imageService) {
        this.modelMapper = modelMapper;
        this.imageService = imageService;
    }

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public ModelAndView index(ModelAndView modelAndView) {

        ImageServiceModel image1 = this.imageService.findImageByName("lebron3");
        ImageServiceModel image2 = this.imageService.findImageByName("messi");
        ImageServiceModel image3 = this.imageService.findImageByName("federer");

        modelAndView.addObject("image1", image1.getImageURL());
        modelAndView.addObject("image2", image2.getImageURL());
        modelAndView.addObject("image3", image3.getImageURL());

        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView home(ModelAndView modelAndView) {

        ImageServiceModel image1 = this.imageService.findImageByName("lebron3");
        ImageServiceModel image2 = this.imageService.findImageByName("messi");
        ImageServiceModel image3 = this.imageService.findImageByName("federer");

        modelAndView.addObject("image1", image1.getImageURL());
        modelAndView.addObject("image2", image2.getImageURL());
        modelAndView.addObject("image3", image3.getImageURL());

        modelAndView.setViewName("home");
        return modelAndView;
    }
}
