package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.ImageCreateBindingModel;
import com.softuni.sportify.domain.models.binding_models.ImageEditBindingModel;
import com.softuni.sportify.domain.models.binding_models.SettingCreateBindingModel;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SettingServiceModel;
import com.softuni.sportify.domain.models.view_models.ImageViewModel;
import com.softuni.sportify.services.CloudinaryService;
import com.softuni.sportify.services.ImageService;
import com.softuni.sportify.services.SettingsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.SettingsControllerConstants.*;
import static com.softuni.sportify.constants.AuthConstants.*;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    private final ImageService imageService;
    private final SettingsService settingsService;

    @Autowired
    public SettingsController(ModelMapper modelMapper,
                              CloudinaryService cloudinaryService,
                              ImageService imageService,
                              SettingsService settingsService) {
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.imageService = imageService;
        this.settingsService = settingsService;
    }

    @GetMapping("/create-new-setting")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView createImage(ModelAndView modelAndView) {
        modelAndView.setViewName(VIEW_CREATE_NEW_SETTING);
        return modelAndView;
    }

    @PostMapping("/create-new-setting")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView createImageConfirmed(@ModelAttribute SettingCreateBindingModel settingCreateBindingModel,
                                             ModelAndView modelAndView) throws IOException {

        SettingServiceModel settingServiceModel = this.modelMapper
                .map(settingCreateBindingModel, SettingServiceModel.class);

        SettingServiceModel newSettingServiceModel = this.settingsService.createNewSetting(settingServiceModel);

        modelAndView.setViewName(REDIRECT_TO_ADD_IMAGES_INDEX_CAROUSEL + newSettingServiceModel.getId());
        return modelAndView;
    }

    @GetMapping("/add-images-index-carousel/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView addImagesIndexCarousel(@PathVariable String id,
                                               ModelAndView modelAndView) {
        SettingServiceModel settingServiceModel = this.settingsService.findByID(id);
        modelAndView.addObject("settingServiceModel", settingServiceModel);

        modelAndView.setViewName(VIEW_ADD_IMAGES_INDEX_CAROUSEL);
        return modelAndView;
    }

    @PostMapping("/add-images-index-carousel/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView addImagesIndexCarouselConfirm(@PathVariable String id,
                                                      @ModelAttribute ImageCreateBindingModel imageCreateBindingModel,
                                                      ModelAndView modelAndView) throws IOException {

        MultipartFile multipartFile = imageCreateBindingModel.getImage();
        ImageServiceModel imageServiceModel = this.imageService
                .createImageMultipartFile(multipartFile, imageCreateBindingModel.getName());

        SettingServiceModel settingServiceModel = this.settingsService.findByID(id);
        this.settingsService.addIndexCarouselImage(settingServiceModel, imageServiceModel);

        modelAndView.setViewName(REDIRECT_TO_ADD_IMAGES_INDEX_CAROUSEL + settingServiceModel.getId());
        return modelAndView;
    }

    @GetMapping("/add-images-home-carousel/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView addImagesHomeCarousel(@PathVariable String id,
                                               ModelAndView modelAndView) {
        SettingServiceModel settingServiceModel = this.settingsService.findByID(id);
        modelAndView.addObject("settingServiceModel", settingServiceModel);

        modelAndView.setViewName(VIEW_ADD_IMAGES_HOME_CAROUSEL);
        return modelAndView;
    }

    @PostMapping("/add-images-home-carousel/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView addImagesHomeCarouselConfirm(@PathVariable String id,
                                                      @ModelAttribute ImageCreateBindingModel imageCreateBindingModel,
                                                      ModelAndView modelAndView) throws IOException {

        MultipartFile multipartFile = imageCreateBindingModel.getImage();
        ImageServiceModel imageServiceModel = this.imageService
                .createImageMultipartFile(multipartFile, imageCreateBindingModel.getName());

        SettingServiceModel settingServiceModel = this.settingsService.findByID(id);
        this.settingsService.addHomeCarouselImage(settingServiceModel, imageServiceModel);

        modelAndView.setViewName(REDIRECT_TO_ADD_IMAGES_HOME_CAROUSEL + settingServiceModel.getId());
        return modelAndView;
    }

    @GetMapping("/all-setting-images")
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
