package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.SportCenterCreateBindingModel;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import com.softuni.sportify.services.ImageService;
import com.softuni.sportify.services.SportCenterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static com.softuni.sportify.constants.SportCentersControllerConstants.*;

@Controller
@RequestMapping("/sport-centers")
public class SportCentersController {

    private final ImageService imageService;
    private final ModelMapper modelMapper;
    private final SportCenterService sportCenterService;

    @Autowired
    public SportCentersController(ImageService imageService,
                                  ModelMapper modelMapper,
                                  SportCenterService sportCenterService) {
        this.imageService = imageService;
        this.modelMapper = modelMapper;
        this.sportCenterService = sportCenterService;
    }

    @GetMapping("/create-sport-center")
    public ModelAndView createSportCenter(ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_CREATE_SPORT_CENTER);
        return modelAndView;
    }

    @PostMapping("/create-sport-center")
    public ModelAndView createSportCenterConfirmed(@ModelAttribute
                                                           SportCenterCreateBindingModel sportCenterCreateBindingModel,
                                                   ModelAndView modelAndView) throws IOException {

        ImageServiceModel descriptionImageServiceModel = this.imageService.createImageMultipartFile(
                sportCenterCreateBindingModel.getDescriptionImage(), sportCenterCreateBindingModel.getName());

        ImageServiceModel iconImageServiceModel = this.imageService.createImageMultipartFile(
                sportCenterCreateBindingModel.getIconImage(), sportCenterCreateBindingModel.getName());

        SportCenterServiceModel sportCenterServiceModel = this.modelMapper.map(
                sportCenterCreateBindingModel, SportCenterServiceModel.class);

        SportCenterServiceModel newSportCenterServiceModel = this.sportCenterService.createSportCenter(
                sportCenterServiceModel, descriptionImageServiceModel, iconImageServiceModel);

        modelAndView.setViewName(REDIRECT_TO_SPORT_CENTER_DETAILS + newSportCenterServiceModel.getId());
        return modelAndView;
    }

    @GetMapping("/sport-center-details/{id}")
    public ModelAndView sportCenterDetails(@PathVariable String id,
                                           ModelAndView modelAndView) {



        modelAndView.setViewName(VIEW_SPORT_CENTER_DETAILS);
        return modelAndView;
    }
}
