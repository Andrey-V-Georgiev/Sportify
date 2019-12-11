package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.ImageCreateBindingModel;
import com.softuni.sportify.domain.models.binding_models.ImageEditBindingModel;
import com.softuni.sportify.domain.models.binding_models.SportCreateBindingModel;
import com.softuni.sportify.domain.models.binding_models.SportEditBindingModel;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.domain.models.view_models.ImageViewModel;
import com.softuni.sportify.domain.models.view_models.SportViewModel;
import com.softuni.sportify.services.ImageService;
import com.softuni.sportify.services.SportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.AuthConstants.HAS_ROLE_ADMIN;
import static com.softuni.sportify.constants.SportsControllerConstants.*;

@Controller
@RequestMapping("/sports")
public class SportsController {

    private final ModelMapper modelMapper;
    private final SportService sportService;
    private final ImageService imageService;

    @Autowired
    public SportsController(ModelMapper modelMapper,
                            SportService sportService,
                            ImageService imageService) {
        this.modelMapper = modelMapper;
        this.sportService = sportService;
        this.imageService = imageService;
    }

    @GetMapping("/create-sport")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView addNewSport(ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_CREATE_SPORT);
        return modelAndView;
    }

    @PostMapping("/create-sport")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editImageConfirmed(
            @ModelAttribute SportCreateBindingModel sportCreateBindingModel,
            ModelAndView modelAndView) throws IOException {

        SportServiceModel sportServiceModel = this.modelMapper.map(sportCreateBindingModel, SportServiceModel.class);
        ImageServiceModel iconImageServiceModel = this.imageService
                .createImageMultipartFile(sportCreateBindingModel.getIconImage(), sportCreateBindingModel.getName());
        SportServiceModel newSportServiceModel = this.sportService
                .createSport(sportServiceModel, iconImageServiceModel);

        modelAndView.setViewName(REDIRECT_TO_SPORT_DETAILS + newSportServiceModel.getId());
        return modelAndView;
    }

    @GetMapping("/sport-details/{id}")
    public ModelAndView sportDetails(
            @PathVariable String id,
            ModelAndView modelAndView) {

        SportServiceModel sportServiceModel = this.sportService.findByID(id);
        SportViewModel sportViewModel = this.modelMapper.map(sportServiceModel, SportViewModel.class);
        modelAndView.addObject("sportViewModel", sportViewModel);

        modelAndView.setViewName(VIEW_SPORT_DETAILS);
        return modelAndView;
    }

    @GetMapping("/guests-sport-details/{id}")
    public ModelAndView guestsSportDetails(
            @PathVariable String id,
            ModelAndView modelAndView) {

        SportServiceModel sportServiceModel = this.sportService.findByID(id);
        SportViewModel sportViewModel = this.modelMapper.map(sportServiceModel, SportViewModel.class);
        modelAndView.addObject("sportViewModel", sportViewModel);

        modelAndView.setViewName(VIEW_GUESTS_SPORT_DETAILS);
        return modelAndView;
    }

    @PostMapping("/edit-icon-image/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editIconImage(
            @PathVariable("id") String sportID,
            ImageEditBindingModel imageEditBindingModel,
            ModelAndView modelAndView) {

        SportServiceModel sportServiceModel = this.sportService.findByID(sportID);
        sportServiceModel.getIconImage().setName(imageEditBindingModel.getName());
        SportServiceModel updatedSportServiceModel = this.sportService.editIconImage(sportServiceModel);
        SportViewModel sportViewModel = this.modelMapper.map(updatedSportServiceModel, SportViewModel.class);

        modelAndView.addObject("sportViewModel", sportViewModel);

        modelAndView.setViewName(REDIRECT_TO_SPORT_DETAILS + sportID);
        return modelAndView;
    }

    @PostMapping("/edit-description/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editDescription(
            @PathVariable("id") String sportID,
            SportEditBindingModel sportEditBindingModel,
            ModelAndView modelAndView) {

        SportServiceModel sportServiceModel = this.sportService.findByID(sportID);
        sportServiceModel.setSportDescription(sportEditBindingModel.getSportDescription());
        SportServiceModel updatedSportServiceModel = this.sportService.updateSportDescription(sportServiceModel);
        SportViewModel sportViewModel = this.modelMapper.map(updatedSportServiceModel, SportViewModel.class);

        modelAndView.addObject("sportViewModel", sportViewModel);

        modelAndView.setViewName(REDIRECT_TO_SPORT_DETAILS + sportID);
        return modelAndView;
    }

    @PostMapping("/add-sport-images/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView addSportImages(
            @PathVariable("id") String sportID,
            ImageCreateBindingModel imageCreateBindingModel,
            ModelAndView modelAndView) throws IOException {

        SportServiceModel sportServiceModel = this.sportService.findByID(sportID);
        ImageServiceModel imageServiceModel = this.imageService
                .createImageMultipartFile(imageCreateBindingModel.getImage(), sportServiceModel.getName());
        SportServiceModel updatedSportServiceModel = this.sportService
                .addSportImage(sportServiceModel, imageServiceModel);
        SportViewModel sportViewModel = this.modelMapper.map(updatedSportServiceModel, SportViewModel.class);

        modelAndView.addObject("sportViewModel", sportViewModel);

        modelAndView.setViewName(REDIRECT_TO_SPORT_DETAILS + sportID);
        return modelAndView;
    }

    @GetMapping("/show-all-sports")
    public ModelAndView showAllSports(ModelAndView modelAndView) {

        List<SportViewModel> sportViewModels = this.sportService.findAllSports()
                .stream()
                .map(s -> this.modelMapper.map(s, SportViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("sportViewModels", sportViewModels);

        modelAndView.setViewName(VIEW_SHOW_ALL_SPORTS);
        return modelAndView;
    }

    @GetMapping("/edit-sport-image/{sportID}/{imageID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editSportImage(
            @PathVariable("sportID") String sportID,
            @PathVariable("imageID") String imageID,
            ModelAndView modelAndView) {

        ImageServiceModel imageServiceModel = this.imageService.findImageByID(imageID);
        ImageViewModel imageViewModel = this.modelMapper.map(imageServiceModel, ImageViewModel.class);
        imageViewModel.setOwnerObjectID(sportID);

        modelAndView.addObject("imageViewModel", imageViewModel);

        modelAndView.setViewName(VIEW_EDIT_SPORT_IMAGE);
        return modelAndView;
    }

    @PostMapping("/edit-sport-image")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editSportImageConfirmed(
            @ModelAttribute ImageEditBindingModel imageEditBindingModel,
            ModelAndView modelAndView) throws IOException {

        this.imageService.editImage(this.modelMapper.map(imageEditBindingModel, ImageServiceModel.class));

        modelAndView.setViewName(REDIRECT_TO_SPORT_DETAILS + imageEditBindingModel.getOwnerObjectID());
        return modelAndView;
    }

    @PostMapping("/delete-sport-image/{sportID}/{imageID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView deleteSportImage(
            @PathVariable("sportID") String sportID,
            @PathVariable("imageID") String imageID,
            ModelAndView modelAndView) throws Exception {

        this.sportService.deleteSportImage(sportID, imageID);
        this.imageService.deleteImage(imageID);

        modelAndView.setViewName(REDIRECT_TO_SPORT_DETAILS + sportID);
        return modelAndView;
    }

    @PostMapping("/delete-sport/{sportID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView deleteSport(
            @PathVariable("sportID") String sportID,
            ModelAndView modelAndView) throws Exception {

        SportServiceModel sportServiceModel = this.sportService.findByID(sportID);
        this.sportService.deleteSport(sportServiceModel);

        modelAndView.setViewName(REDIRECT_TO_SHOW_ALL_SPORTS);
        return modelAndView;
    }
}
