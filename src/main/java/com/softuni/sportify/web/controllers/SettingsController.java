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

import static com.softuni.sportify.constants.ImagesControllerConstants.*;
import static com.softuni.sportify.constants.AuthConstants.*;

@Controller
@RequestMapping("/images")
public class SettingsController {

    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    private final ImageService imageService;

    @Autowired
    public SettingsController(ModelMapper modelMapper, CloudinaryService cloudinaryService, ImageService imageService) {
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.imageService = imageService;
    }

    @GetMapping("/create-image")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView createImage(ModelAndView modelAndView) {
        modelAndView.setViewName(VIEW_CREATE_SETTINGS_IMAGE);
        return modelAndView;
    }

    @PostMapping("/create-image")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView createImageConfirmed(@ModelAttribute ImageCreateBindingModel imageCreateBindingModel,
                                             ModelAndView modelAndView) throws IOException {

        ImageServiceModel imageServiceModel = this.modelMapper.map(imageCreateBindingModel, ImageServiceModel.class);
        imageServiceModel.setImageURL(this.cloudinaryService.uploadImage(imageCreateBindingModel.getImage()));
        this.imageService.createImage(imageServiceModel);

        modelAndView.setViewName(REDIRECT_TO_ALL_IMAGES);
        return modelAndView;
    }

    @GetMapping("/all-images")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView allImages(ModelAndView modelAndView) {

        List<ImageViewModel> imageViewModels = this.imageService.findAll()
                .stream()
                .map(i -> this.modelMapper.map(i, ImageViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("imageViewModels", imageViewModels);

        modelAndView.setViewName(VIEW_ALL_IMAGES);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editImage(@PathVariable(name = "id") String id,
                                  @ModelAttribute ImageEditBindingModel imageEditBindingModel,
                                  ModelAndView modelAndView) {

        ImageServiceModel imageServiceModel = this.imageService.findImageByID(id);
        this.modelMapper.map(imageServiceModel, imageEditBindingModel);
        modelAndView.addObject("imageEditBindingModel", imageEditBindingModel);

        modelAndView.setViewName(VIEW_EDIT_IMAGE);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editImageConfirmed(@PathVariable(name = "id") String id,
                                           @ModelAttribute ImageEditBindingModel imageEditBindingModel,
                                           BindingResult bindingResult,
                                           ModelAndView modelAndView) throws IOException {
        this.imageService.editImage(this.modelMapper.map(imageEditBindingModel, ImageServiceModel.class));

        modelAndView.setViewName(REDIRECT_TO_ALL_IMAGES);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView deleteImage(@PathVariable(name = "id") String id,
                                    ModelAndView modelAndView) throws Exception {
        this.imageService.deleteImage(id);
        modelAndView.setViewName(REDIRECT_TO_ALL_IMAGES);
        return modelAndView;
    }
}
