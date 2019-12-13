package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.ImageCreateBindingModel;
import com.softuni.sportify.domain.models.binding_models.ImageEditBindingModel;
import com.softuni.sportify.domain.models.binding_models.ThemeCreateBindingModel;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.ThemeServiceModel;
import com.softuni.sportify.domain.models.view_models.ImageViewModel;
import com.softuni.sportify.domain.models.view_models.ThemeViewModel;
import com.softuni.sportify.services.ImageService;
import com.softuni.sportify.services.ThemeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.ThemesControllerConstants.*;
import static com.softuni.sportify.constants.AuthConstants.*;

@Controller
@RequestMapping("/themes")
public class ThemesController {

    private final ModelMapper modelMapper;
    private final ImageService imageService;
    private final ThemeService themeService;

    @Autowired
    public ThemesController(ModelMapper modelMapper,
                            ImageService imageService,
                            ThemeService themeService) {
        this.modelMapper = modelMapper;
        this.imageService = imageService;
        this.themeService = themeService;
    }

    @GetMapping("/create-new-theme")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView createNewTheme(ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_CREATE_NEW_THEME);
        return modelAndView;
    }

    @PostMapping("/create-new-theme")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView createNewThemeConfirmed(
            @ModelAttribute ThemeCreateBindingModel themeCreateBindingModel,
            ModelAndView modelAndView) throws IOException {

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(themeCreateBindingModel, ThemeServiceModel.class);
        ImageServiceModel imageServiceModel = this.imageService
                .createImageMultipartFile(themeCreateBindingModel.getIconImage(), themeCreateBindingModel.getName());
        ThemeServiceModel newThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);

        modelAndView.setViewName(REDIRECT_TO_THEME_DETAILS + newThemeServiceModel.getId());
        return modelAndView;
    }

    @PostMapping("/add-images-index-carousel/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView addImagesIndexCarouselConfirm(
            @PathVariable String id,
            @ModelAttribute ImageCreateBindingModel imageCreateBindingModel,
            ModelAndView modelAndView) throws IOException {

        ImageServiceModel imageServiceModel = this.imageService
                .createImageMultipartFile(imageCreateBindingModel.getImage(), imageCreateBindingModel.getName());
        ThemeServiceModel themeServiceModel = this.themeService.findByID(id);
        this.themeService.addIndexCarouselImage(themeServiceModel, imageServiceModel);

        modelAndView.setViewName(REDIRECT_TO_THEME_DETAILS + themeServiceModel.getId());
        return modelAndView;
    }

    @PostMapping("/add-images-home-carousel/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView addImagesHomeCarouselConfirm(
            @PathVariable String id,
            @ModelAttribute ImageCreateBindingModel imageCreateBindingModel,
            ModelAndView modelAndView) throws IOException {

        ImageServiceModel imageServiceModel = this.imageService
                .createImageMultipartFile(imageCreateBindingModel.getImage(), imageCreateBindingModel.getName());
        ThemeServiceModel themeServiceModel = this.themeService.findByID(id);
        this.themeService.addHomeCarouselImage(themeServiceModel, imageServiceModel);

        modelAndView.setViewName(REDIRECT_TO_THEME_DETAILS + themeServiceModel.getId());
        return modelAndView;
    }

    @PostMapping("/add-admin-panel-images/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView addAdminPanelImagesConfirm(
            @PathVariable String id,
            @ModelAttribute ImageCreateBindingModel imageCreateBindingModel,
            ModelAndView modelAndView) throws IOException {

        ImageServiceModel imageServiceModel = this.imageService
                .createImageMultipartFile(imageCreateBindingModel.getImage(), imageCreateBindingModel.getName());
        ThemeServiceModel themeServiceModel = this.themeService.findByID(id);
        this.themeService.addAdminPanelImages(themeServiceModel, imageServiceModel);

        modelAndView.setViewName(REDIRECT_TO_THEME_DETAILS + themeServiceModel.getId());
        return modelAndView;
    }

    @GetMapping("/show-all-themes")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView showAllThemes(ModelAndView modelAndView) {

        List<ThemeViewModel> themeViewModels = this.themeService.findAll()
                .stream()
                .map(t -> this.modelMapper.map(t, ThemeViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("themeViewModels", themeViewModels);

        modelAndView.setViewName(VIEW_SHOW_ALL_THEMES);
        return modelAndView;
    }

    @GetMapping("/show-theme-details/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView showThemeDetails(
            @PathVariable String id,
            ModelAndView modelAndView) {

        ThemeViewModel themeViewModel = this.modelMapper
                .map(this.themeService.findByID(id), ThemeViewModel.class);

        modelAndView.addObject("themeViewModel", themeViewModel);

        modelAndView.setViewName(VIEW_THEME_DETAILS);
        return modelAndView;
    }


    @GetMapping("/edit-theme-image/{themeID}/{imageID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editThemeImage(
            @PathVariable("themeID") String themeID,
            @PathVariable("imageID") String imageID,
            ModelAndView modelAndView) {

        ImageServiceModel imageServiceModel = this.imageService.findImageByID(imageID);
        ImageViewModel imageViewModel = this.modelMapper.map(imageServiceModel, ImageViewModel.class);
        imageViewModel.setOwnerObjectID(themeID);

        modelAndView.addObject("imageViewModel", imageViewModel);

        modelAndView.setViewName(VIEW_EDIT_THEME_IMAGE);
        return modelAndView;
    }

    @PostMapping("/edit-theme-image")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editThemeImageConfirmed(
            @ModelAttribute ImageEditBindingModel imageEditBindingModel,
            ModelAndView modelAndView) throws IOException {

        this.imageService.editImage(this.modelMapper.map(imageEditBindingModel, ImageServiceModel.class));

        modelAndView.setViewName(REDIRECT_TO_THEME_DETAILS + imageEditBindingModel.getOwnerObjectID());
        return modelAndView;
    }

    @PostMapping("/delete-theme-image/{themeID}/{imageID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView deleteThemeImage(
            @PathVariable("themeID") String themeID,
            @PathVariable("imageID") String imageID,
            ModelAndView modelAndView) throws Exception {

        this.themeService.deleteThemeImage(themeID, imageID);
        this.imageService.deleteImage(imageID);

        modelAndView.setViewName(REDIRECT_TO_THEME_DETAILS + themeID);
        return modelAndView;
    }

    @PostMapping("/delete-admin-panel-images/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView deleteAdminPanelImages(
            @PathVariable("id") String themeID,
            ModelAndView modelAndView) throws Exception {

        ThemeServiceModel themeServiceModel = this.themeService.findByID(themeID);
        this.themeService.deleteAdminPanelImages(themeServiceModel);

        modelAndView.setViewName(REDIRECT_TO_THEME_DETAILS + themeID);
        return modelAndView;
    }

    @PostMapping("/delete-theme/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView deleteTheme(
            @PathVariable String id,
            ModelAndView modelAndView) throws Exception {

        this.themeService.deleteTheme(id);

        modelAndView.setViewName(REDIRECT_TO_SHOW_ALL_THEMES);
        return modelAndView;
    }

    @PostMapping("/activate-theme/{id}")
    public ModelAndView activateTheme(
            @PathVariable("id") String themeID,
            ModelAndView modelAndView ) {

        ThemeServiceModel themeServiceModel = this.themeService.findByID(themeID);
        ThemeServiceModel activeThemeServiceModel = this.themeService.activateTheme(themeServiceModel);

        modelAndView.setViewName(REDIRECT_TO_SHOW_ALL_THEMES);
        return modelAndView;
    }
}
