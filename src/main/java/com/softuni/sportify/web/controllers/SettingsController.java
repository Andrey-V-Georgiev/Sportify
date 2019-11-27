package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.ImageCreateBindingModel;
import com.softuni.sportify.domain.models.binding_models.ImageEditBindingModel;
import com.softuni.sportify.domain.models.binding_models.SettingCreateBindingModel;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SettingServiceModel;
import com.softuni.sportify.services.ImageService;
import com.softuni.sportify.services.SettingsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

import static com.softuni.sportify.constants.SettingsControllerConstants.*;
import static com.softuni.sportify.constants.AuthConstants.*;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final ModelMapper modelMapper;
    private final ImageService imageService;
    private final SettingsService settingsService;

    @Autowired
    public SettingsController(ModelMapper modelMapper,
                              ImageService imageService,
                              SettingsService settingsService) {
        this.modelMapper = modelMapper;
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

        modelAndView.setViewName(REDIRECT_TO_SETTING_DETAILS + newSettingServiceModel.getId());
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

        modelAndView.setViewName(REDIRECT_TO_SETTING_DETAILS + settingServiceModel.getId());
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

        modelAndView.setViewName(REDIRECT_TO_SETTING_DETAILS + settingServiceModel.getId());
        return modelAndView;
    }

    @PostMapping("/add-admin-panel-images/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView addAdminPanelImagesConfirm(@PathVariable String id,
                                                   @ModelAttribute ImageCreateBindingModel imageCreateBindingModel,
                                                   ModelAndView modelAndView) throws IOException {

        MultipartFile multipartFile = imageCreateBindingModel.getImage();
        ImageServiceModel imageServiceModel = this.imageService
                .createImageMultipartFile(multipartFile, imageCreateBindingModel.getName());

        SettingServiceModel settingServiceModel = this.settingsService.findByID(id);
        this.settingsService.addAdminPanelImages(settingServiceModel, imageServiceModel);

        modelAndView.setViewName(REDIRECT_TO_SETTING_DETAILS + settingServiceModel.getId());
        return modelAndView;
    }

    @GetMapping("/show-all-settings")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView showAllSettings(ModelAndView modelAndView) {

        List<SettingServiceModel> allSettingServiceModels = this.settingsService.findAll();
        modelAndView.addObject("allSettingServiceModels", allSettingServiceModels);

        modelAndView.setViewName(VIEW_SHOW_ALL_SETTINGS);
        return modelAndView;
    }

    @GetMapping("/show-setting-details/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView showSettingDetails(@PathVariable String id,
                                           ModelAndView modelAndView) {

        SettingServiceModel settingServiceModel = this.settingsService.findByID(id);
        modelAndView.addObject("settingServiceModel", settingServiceModel);

        modelAndView.setViewName(VIEW_SETTING_DETAILS);
        return modelAndView;
    }


    @GetMapping("/edit-setting-image/{settingID}/{imageID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editSettingImage(@PathVariable("settingID") String settingID,
                                         @PathVariable("imageID") String imageID,
                                         @ModelAttribute ImageEditBindingModel imageEditBindingModel,
                                         ModelAndView modelAndView) {

        ImageServiceModel imageServiceModel = this.imageService.findImageByID(imageID);
        this.modelMapper.map(imageServiceModel, imageEditBindingModel);
        imageEditBindingModel.setOwnerObjectID(settingID);
        modelAndView.addObject("imageEditBindingModel", imageEditBindingModel);

        modelAndView.setViewName(VIEW_EDIT_IMAGE);
        return modelAndView;
    }

    @PostMapping("/edit-setting-image")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editSettingImageConfirmed(@ModelAttribute ImageEditBindingModel imageEditBindingModel,
                                                  ModelAndView modelAndView) throws IOException {

        this.imageService.editImage(this.modelMapper.map(imageEditBindingModel, ImageServiceModel.class));

        modelAndView.setViewName(REDIRECT_TO_SETTING_DETAILS + imageEditBindingModel.getOwnerObjectID());
        return modelAndView;
    }

    @PostMapping("/delete-setting-image/{settingID}/{imageID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView deleteImage(@PathVariable("settingID") String settingID,
                                    @PathVariable("imageID") String imageID,
                                    ModelAndView modelAndView) throws Exception {

        this.settingsService.deleteImage(settingID, imageID);
        this.imageService.deleteImage(imageID);
        modelAndView.setViewName(REDIRECT_TO_SETTING_DETAILS + settingID);
        return modelAndView;
    }

    @PostMapping("/delete-setting/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView deleteSetting(@PathVariable String id,
                                      ModelAndView modelAndView) throws Exception {

        this.settingsService.deleteSetting(id);
        modelAndView.setViewName(REDIRECT_TO_SHOW_ALL_SETTINGS);
        return modelAndView;
    }
}
