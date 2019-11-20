package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.ImageCreateBindingModel;
import com.softuni.sportify.domain.models.binding_models.ImageEditBindingModel;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.view_models.ImageViewModel;
import com.softuni.sportify.services.CloudinaryService;
import com.softuni.sportify.services.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/images")
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
    public ModelAndView createImageConfirmed(@ModelAttribute ImageCreateBindingModel model,
                                             ModelAndView modelAndView) throws IOException {

        ImageServiceModel imageServiceModel = this.modelMapper.map(model, ImageServiceModel.class);

        imageServiceModel.setImageURL(this.cloudinaryService.uploadImage(model.getImage()));
        imageServiceModel.setPublicID(this.imageService.obtainPublicID(imageServiceModel.getImageURL()));

        this.imageService.createImage(imageServiceModel);

        modelAndView.setViewName("redirect:/images/all-images");
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

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView editImage(@PathVariable(name = "id") String id,
                                  @ModelAttribute ImageEditBindingModel imageEditBindingModel,
                                  ModelAndView modelAndView) {

        ImageServiceModel imageServiceModel = this.imageService.findImageByID(id);
        this.modelMapper.map(imageServiceModel, imageEditBindingModel);
        modelAndView.addObject("imageEditBindingModel", imageEditBindingModel);

        modelAndView.setViewName("images/edit-image");
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView editImageConfirmed(@PathVariable(name = "id") String id,
                                           @ModelAttribute ImageEditBindingModel imageEditBindingModel,
                                           BindingResult bindingResult,
                                           ModelAndView modelAndView) throws IOException {
        this.imageService.editImage(this.modelMapper.map(imageEditBindingModel, ImageServiceModel.class));

        modelAndView.setViewName("redirect:/images/all-images");
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView deleteImage(@PathVariable(name = "id") String id,
                                    ModelAndView modelAndView) {
        this.imageService.deleteImage(id);
        modelAndView.setViewName("redirect:/images/all-images");
        return modelAndView;
    }
}
