package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.ImageCreateBindingModel;
import com.softuni.sportify.domain.models.binding_models.ImageEditBindingModel;
import com.softuni.sportify.domain.models.binding_models.ThemeCreateBindingModel;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.ThemeServiceModel;
import com.softuni.sportify.domain.models.view_models.ImageViewModel;
import com.softuni.sportify.domain.models.view_models.ThemeViewModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.DeleteException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import com.softuni.sportify.services.ImageService;
import com.softuni.sportify.services.ThemeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

        modelAndView.addObject("themeCreateBindingModel", new ThemeCreateBindingModel());
        modelAndView.setViewName(VIEW_CREATE_NEW_THEME);
        return modelAndView;
    }

    @PostMapping("/create-new-theme")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView createNewThemeConfirmed(
            @Valid
            @ModelAttribute ThemeCreateBindingModel themeCreateBindingModel,
            BindingResult themeBindingResult,
            ModelAndView modelAndView) throws IOException, CreateException {

//        if(themeBindingResult.hasErrors()) {
//            modelAndView.addObject("themeCreateBindingModel", themeCreateBindingModel);
//            modelAndView.setViewName(VIEW_CREATE_NEW_THEME);
//            return modelAndView;
//        }

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
            @Valid
            @ModelAttribute ImageCreateBindingModel imageCreateBindingModel,
            BindingResult imageBindingResult,
            ModelAndView modelAndView) throws IOException, CreateException, ReadException, UpdateException {

//        if(imageBindingResult.hasErrors()) {
//            ThemeViewModel themeViewModel = this.modelMapper
//                    .map(this.themeService.findByID(id), ThemeViewModel.class);
//            themeViewModel.setSection(1);
//            modelAndView.addObject("themeViewModel", themeViewModel);
//            modelAndView.addObject("imageCreateBindingModel", imageCreateBindingModel);
//            modelAndView.setViewName(VIEW_THEME_DETAILS);
//            return modelAndView;
//        }

        ImageServiceModel imageServiceModel = this.imageService
                .createImageMultipartFile(imageCreateBindingModel.getImage(),imageCreateBindingModel.getName());
        ThemeServiceModel themeServiceModel = this.themeService.findByID(id);
        this.themeService.addIndexCarouselImage(themeServiceModel, imageServiceModel);

        modelAndView.setViewName(REDIRECT_TO_THEME_DETAILS + themeServiceModel.getId());
        return modelAndView;
    }

    @PostMapping("/add-images-home-carousel/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView addImagesHomeCarouselConfirm(
            @PathVariable String id,
            @Valid
            @ModelAttribute ImageCreateBindingModel imageCreateBindingModel,
            BindingResult imageBindingResult,
            ModelAndView modelAndView) throws IOException, CreateException, ReadException, UpdateException {

//        if(imageBindingResult.hasErrors()) {
//            ThemeViewModel themeViewModel = this.modelMapper
//                    .map(this.themeService.findByID(id), ThemeViewModel.class);
//            themeViewModel.setSection(2);
//            modelAndView.addObject("themeViewModel", themeViewModel);
//            modelAndView.addObject("imageCreateBindingModel", imageCreateBindingModel);
//            modelAndView.setViewName(VIEW_THEME_DETAILS);
//            return modelAndView;
//        }

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
            @Valid
            @ModelAttribute ImageCreateBindingModel imageCreateBindingModel,
            BindingResult imageBindingResult,
            ModelAndView modelAndView) throws IOException, CreateException, ReadException, UpdateException {

//        if(imageBindingResult.hasErrors()) {
//            ThemeViewModel themeViewModel = this.modelMapper
//                    .map(this.themeService.findByID(id), ThemeViewModel.class);
//            themeViewModel.setSection(3);
//            modelAndView.addObject("themeViewModel", themeViewModel);
//            modelAndView.addObject("imageCreateBindingModel", imageCreateBindingModel);
//            modelAndView.setViewName(VIEW_THEME_DETAILS);
//            return modelAndView;
//        }

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
            ModelAndView modelAndView) throws ReadException {

        ThemeViewModel themeViewModel = this.modelMapper
                .map(this.themeService.findByID(id), ThemeViewModel.class);

        modelAndView.addObject("themeViewModel", themeViewModel);
        modelAndView.addObject("imageCreateBindingModel", new ImageCreateBindingModel());

        modelAndView.setViewName(VIEW_THEME_DETAILS);
        return modelAndView;
    }


    @GetMapping("/edit-theme-image/{themeID}/{imageID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editThemeImage(
            @PathVariable("themeID") String themeID,
            @PathVariable("imageID") String imageID,
            ModelAndView modelAndView) throws ReadException {

        ThemeViewModel themeViewModel = this.modelMapper
                .map(this.themeService.findByID(themeID), ThemeViewModel.class);
        ImageServiceModel imageServiceModel = this.imageService.findImageByID(imageID);
        ImageViewModel imageViewModel = this.modelMapper.map(imageServiceModel, ImageViewModel.class);

        modelAndView.addObject("themeViewModel", themeViewModel);
        modelAndView.addObject("imageViewModel", imageViewModel);
        modelAndView.addObject("imageEditBindingModel", new ImageEditBindingModel());

        modelAndView.setViewName(VIEW_EDIT_THEME_IMAGE);
        return modelAndView;
    }

    @PostMapping("/edit-theme-image/{themeID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editThemeImageConfirmed(
            @PathVariable("themeID") String themeID,
            @Valid
            @ModelAttribute ImageEditBindingModel imageEditBindingModel,
            BindingResult imageBindingResult,
            ModelAndView modelAndView) throws IOException, UpdateException {

//        if(imageBindingResult.hasErrors()) {
//            ThemeViewModel themeViewModel = this.modelMapper
//                    .map(this.themeService.findByID(themeID), ThemeViewModel.class);
//            ImageViewModel imageViewModel = this.modelMapper.map(imageEditBindingModel, ImageViewModel.class);
//            modelAndView.addObject("themeViewModel", themeViewModel);
//            modelAndView.addObject("imageViewModel", imageViewModel);
//            modelAndView.addObject("imageEditBindingModel", imageEditBindingModel);
//            modelAndView.setViewName(VIEW_EDIT_THEME_IMAGE);
//            return modelAndView;
//        }

        this.imageService.editImage(this.modelMapper.map(imageEditBindingModel, ImageServiceModel.class));

        modelAndView.setViewName(REDIRECT_TO_THEME_DETAILS + themeID);
        return modelAndView;
    }

    @PostMapping("/delete-theme-image/{themeID}/{imageID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView deleteThemeImage(
            @PathVariable("themeID") String themeID,
            @PathVariable("imageID") String imageID,
            ModelAndView modelAndView) throws Exception, UpdateException, DeleteException {

        this.themeService.deleteThemeImage(themeID, imageID);
        this.imageService.deleteImage(imageID);

        modelAndView.setViewName(REDIRECT_TO_THEME_DETAILS + themeID);
        return modelAndView;
    }

    @PostMapping("/delete-admin-panel-images/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView deleteAdminPanelImages(
            @PathVariable("id") String themeID,
            ModelAndView modelAndView) throws Exception, ReadException, UpdateException {

        ThemeServiceModel themeServiceModel = this.themeService.findByID(themeID);
        this.themeService.deleteAdminPanelImages(themeServiceModel);

        modelAndView.setViewName(REDIRECT_TO_THEME_DETAILS + themeID);
        return modelAndView;
    }

    @PostMapping("/delete-theme/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView deleteTheme(
            @PathVariable String id,
            ModelAndView modelAndView) throws Exception, DeleteException {

        this.themeService.deleteTheme(id);

        modelAndView.setViewName(REDIRECT_TO_SHOW_ALL_THEMES);
        return modelAndView;
    }

    @PostMapping("/activate-theme/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView activateTheme(
            @PathVariable("id") String themeID,
            ModelAndView modelAndView ) throws ReadException, UpdateException {

        ThemeServiceModel themeServiceModel = this.themeService.findByID(themeID);
        ThemeServiceModel activeThemeServiceModel = this.themeService.activateTheme(themeServiceModel);

        modelAndView.setViewName(REDIRECT_TO_SHOW_ALL_THEMES);
        return modelAndView;
    }
}
