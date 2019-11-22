package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.models.binding_models.SportAddNewBindingModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.services.SportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.LinkedHashSet;

import static com.softuni.sportify.constants.AuthConstants.HAS_ROLE_ADMIN;
import static com.softuni.sportify.constants.ImagesControllerConstants.REDIRECT_TO_ALL_IMAGES;
import static com.softuni.sportify.constants.SportsControllerConstants.*;

@Controller
@RequestMapping("/sports")
public class SportsController {

    private final ModelMapper modelMapper;
    private final SportService sportService;

    @Autowired
    public SportsController(ModelMapper modelMapper,
                            SportService sportService) {
        this.modelMapper = modelMapper;
        this.sportService = sportService;
    }

    @GetMapping("/add-new-sport")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView addNewSport(ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_ADD_NEW_SPORT);
        return modelAndView;
    }

    @PostMapping("/add-new-sport")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editImageConfirmed(@ModelAttribute SportAddNewBindingModel sportAddNewBindingModel,
                                           BindingResult bindingResult,
                                           ModelAndView modelAndView) throws IOException {

        SportServiceModel sportServiceModel = this.sportService
                .addNewSport(this.modelMapper.map(sportAddNewBindingModel, SportServiceModel.class));
        modelAndView.addObject("sportServiceModel", sportServiceModel);

        modelAndView.setViewName(REDIRECT_TO_NEW_SPORT_ADD_IMAGES);
        return modelAndView;
    }

    @GetMapping("/new-sport-create-images")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView newSportCreateImages(@ModelAttribute SportServiceModel sportServiceModel,
                                          ModelAndView modelAndView) {

        modelAndView.addObject("sportServiceModel", sportServiceModel);
        modelAndView.setViewName(VIEW_NEW_SPORT_CREATE_IMAGES);
        return modelAndView;
    }


    @GetMapping("/list-all")
    public ModelAndView listAllSports(ModelAndView modelAndView) {
        modelAndView.setViewName(VIEW_ALL_IMAGES_BY_SPORT);
        return modelAndView;
    }
}
