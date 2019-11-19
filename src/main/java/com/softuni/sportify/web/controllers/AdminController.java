package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.ImageBindingModel;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.services.CloudinaryService;
import com.softuni.sportify.services.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    private final ImageService imageService;

    @Autowired
    public AdminController(ModelMapper modelMapper,
                           CloudinaryService cloudinaryService,
                           ImageService imageService) {
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.imageService = imageService;
    }

    @GetMapping("/panel")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView adminPanel(ModelAndView modelAndView) {

        ImageServiceModel usersImage = this.imageService.findImageByName("users2");
        ImageServiceModel imagesImage = this.imageService.findImageByName("images3");
        ImageServiceModel sportCentersImage = this.imageService.findImageByName("sport-centers3");
        ImageServiceModel sportsImage = this.imageService.findImageByName("sports2");

        modelAndView.addObject("usersImage", usersImage.getImageURL());
        modelAndView.addObject("imagesImage", imagesImage.getImageURL());
        modelAndView.addObject("sportCentersImage", sportCentersImage.getImageURL());
        modelAndView.addObject("sportsImage", sportsImage.getImageURL());

        modelAndView.setViewName("admin/admin-panel");
        return modelAndView;
    }

    @GetMapping("/create-image")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView createImage(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/create-image");
        return modelAndView;
    }

    @PostMapping("/create-image")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView createImageConfirmed(@ModelAttribute ImageBindingModel model,
                                              ModelAndView modelAndView) throws IOException {

        ImageServiceModel imageServiceModel = this.modelMapper.map(model, ImageServiceModel.class);
        imageServiceModel.setImageURL(this.cloudinaryService.uploadImage(model.getImage()));
        this.imageService.createImage(imageServiceModel);

        modelAndView.setViewName("redirect:/admin/panel");
        return modelAndView;
    }
}
