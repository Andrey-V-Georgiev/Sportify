package com.softuni.sportify.web.controllers;

import com.softuni.sportify.services.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.softuni.sportify.constants.AuthConstants.*;
import static com.softuni.sportify.constants.HomeControllerConstants.*;

@Controller
public class HomeController {

    private final ModelMapper modelMapper;
    private final ImageService imageService;

    @Autowired
    public HomeController(ModelMapper modelMapper,
                          ImageService imageService) {

        this.modelMapper = modelMapper;
        this.imageService = imageService;
    }

    @GetMapping("/")
    @PreAuthorize(IS_ANONYMOUS)
    public ModelAndView index(ModelAndView modelAndView) {

        modelAndView.addObject("image1", HOME_PAGE_CAROUSEL_IMAGE_1);
        modelAndView.addObject("image2", HOME_PAGE_CAROUSEL_IMAGE_2);
        modelAndView.addObject("image3", HOME_PAGE_CAROUSEL_IMAGE_3);

        modelAndView.setViewName(VIEW_INDEX);
        return modelAndView;
    }

    @GetMapping("/home")
    @PreAuthorize(IS_AUTHENTICATED)
    public ModelAndView home(ModelAndView modelAndView) {

        modelAndView.addObject("image1", HOME_PAGE_CAROUSEL_IMAGE_1);
        modelAndView.addObject("image2", HOME_PAGE_CAROUSEL_IMAGE_2);
        modelAndView.addObject("image3", HOME_PAGE_CAROUSEL_IMAGE_3);

        modelAndView.setViewName(VIEW_HOME);
        return modelAndView;
    }
}
