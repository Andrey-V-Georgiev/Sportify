package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.AddressEditBindingModel;
import com.softuni.sportify.domain.models.binding_models.ImageCreateBindingModel;
import com.softuni.sportify.domain.models.binding_models.SportCenterCreateBindingModel;
import com.softuni.sportify.domain.models.binding_models.SportCenterEditBindingModel;
import com.softuni.sportify.domain.models.service_models.AddressServiceModel;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import com.softuni.sportify.services.AddressService;
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
    private final AddressService addressService;

    @Autowired
    public SportCentersController(ImageService imageService,
                                  ModelMapper modelMapper,
                                  SportCenterService sportCenterService, AddressService addressService) {
        this.imageService = imageService;
        this.modelMapper = modelMapper;
        this.sportCenterService = sportCenterService;
        this.addressService = addressService;
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

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(id);
        modelAndView.addObject("sportCenterServiceModel", sportCenterServiceModel);

        modelAndView.setViewName(VIEW_SPORT_CENTER_DETAILS);
        return modelAndView;
    }

    @PostMapping("/edit-description/{id}")
    public ModelAndView editDescription(@PathVariable("id") String sportCenterID,
                                        @ModelAttribute SportCenterEditBindingModel sportCenterEditBindingModel,
                                        ModelAndView modelAndView) {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);
        sportCenterServiceModel.setDescription(sportCenterEditBindingModel.getDescription());
        SportCenterServiceModel updatedSportCenterServiceModel = this.sportCenterService
                .updateSportCenterDescription(sportCenterServiceModel);

        modelAndView.addObject("sportCenterServiceModel", updatedSportCenterServiceModel);
        modelAndView.setViewName(REDIRECT_TO_SPORT_CENTER_DETAILS + sportCenterID);
        return modelAndView;
    }

    @PostMapping("/add-sport-center-images/{id}")
    public ModelAndView addSportCenterImages(@PathVariable("id") String sportCenterID,
                                             ImageCreateBindingModel imageCreateBindingModel,
                                             ModelAndView modelAndView) throws IOException {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);
        ImageServiceModel imageServiceModel = this.imageService
                .createImageMultipartFile(imageCreateBindingModel.getImage(), sportCenterServiceModel.getName());

        SportCenterServiceModel updatedSportCenterServiceModel = this.sportCenterService
                .addSportCenterImage(sportCenterServiceModel, imageServiceModel);

        modelAndView.addObject("sportCenterServiceModel", updatedSportCenterServiceModel);
        modelAndView.setViewName(REDIRECT_TO_SPORT_CENTER_DETAILS + sportCenterID);
        return modelAndView;
    }

    @PostMapping("/edit-sport-center-address/{id}")
    public ModelAndView editSportCenterAddress(@PathVariable("id") String sportCenterID,
                                               @ModelAttribute AddressEditBindingModel addressEditBindingModel,
                                               ModelAndView modelAndView) throws IOException {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);
        addressEditBindingModel.setId(sportCenterServiceModel.getAddress().getId());
        AddressServiceModel addressServiceModel = this.modelMapper.map(
                addressEditBindingModel, AddressServiceModel.class);
        sportCenterServiceModel.setAddress(this.addressService.editAddress(addressServiceModel));

        SportCenterServiceModel updatedSportCenterServiceModel = this.sportCenterService
                .editSportCenterAddress(sportCenterServiceModel);

        modelAndView.addObject("sportCenterServiceModel", updatedSportCenterServiceModel);
        modelAndView.setViewName(REDIRECT_TO_SPORT_CENTER_DETAILS + sportCenterID);
        return modelAndView;
    }
}
