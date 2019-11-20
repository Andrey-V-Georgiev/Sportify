package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.ImageBindingModel;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.view_models.ImageViewModel;
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
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/image")
public class ImagesController {


    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    private final ImageService imageService;

    @Autowired
    public ImagesController(ModelMapper modelMapper, CloudinaryService cloudinaryService, ImageService imageService) {
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.imageService = imageService;
    }


    @GetMapping("/create-image")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView createImage(ModelAndView modelAndView) {
        modelAndView.setViewName("images/create-image");
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

    @GetMapping("/all-images")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView allImages(ModelAndView modelAndView) {

        List<ImageViewModel> imageViewModels = this.imageService.findAll()
                .stream()
                .map(i -> this.modelMapper.map(i, ImageViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("imageViewModels", imageViewModels);

        modelAndView.setViewName("images/all-images");
        return modelAndView;
    }
}
