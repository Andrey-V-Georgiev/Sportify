package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.ImageCreateBindingModel;
import com.softuni.sportify.domain.models.binding_models.SportCreateBindingModel;
import com.softuni.sportify.domain.models.binding_models.SportEditBindingModel;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.services.CloudinaryService;
import com.softuni.sportify.services.ImageService;
import com.softuni.sportify.services.SportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static com.softuni.sportify.constants.AuthConstants.HAS_ROLE_ADMIN;
import static com.softuni.sportify.constants.SportsControllerConstants.*;

@Controller
@RequestMapping("/sports")
public class SportsController {

    private final ModelMapper modelMapper;
    private final SportService sportService;
    private final ImageService imageService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public SportsController(ModelMapper modelMapper,
                            SportService sportService,
                            ImageService imageService,
                            CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.sportService = sportService;
        this.imageService = imageService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/create-sport")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView addNewSport(ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_CREATE_SPORT);
        return modelAndView;
    }

    @PostMapping("/create-sport")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editImageConfirmed(@ModelAttribute SportCreateBindingModel sportCreateBindingModel,
                                           ModelAndView modelAndView) throws IOException {

        SportServiceModel sportServiceModel = this.modelMapper.map(sportCreateBindingModel, SportServiceModel.class);
        ImageServiceModel descriptionImageServiceModel = this.imageService
                .createImageMultipartFile(sportCreateBindingModel.getDescriptionImage(), sportCreateBindingModel.getName());
        ImageServiceModel iconImageServiceModel = this.imageService
                .createImageMultipartFile(sportCreateBindingModel.getIconImage(), sportCreateBindingModel.getName());

        SportServiceModel newSportServiceModel = this.sportService
                .createSport(sportServiceModel, descriptionImageServiceModel, iconImageServiceModel);

        modelAndView.setViewName(REDIRECT_TO_SPORT_DETAILS + newSportServiceModel.getId());
        return modelAndView;
    }

    @GetMapping("/sport-details/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView sportDetails(@PathVariable String id,
                                     ModelAndView modelAndView) {

        SportServiceModel sportServiceModel = this.sportService.findByID(id);
        modelAndView.addObject("sportServiceModel", sportServiceModel);

        modelAndView.setViewName(VIEW_SPORT_DETAILS);
        return modelAndView;
    }

    @PostMapping("/edit-description/{id}")
    public ModelAndView editDescription(@PathVariable("id") String sportID,
                                        SportEditBindingModel sportEditBindingModel,
                                        ModelAndView modelAndView) {

        SportServiceModel sportServiceModel = this.sportService.findByID(sportID);
        sportServiceModel.setSportDescription(sportEditBindingModel.getSportDescription());
        SportServiceModel updatedSportServiceModel = this.sportService.updateSportDescription(sportServiceModel);

        modelAndView.addObject("sportServiceModel", updatedSportServiceModel);
        modelAndView.setViewName(REDIRECT_TO_SPORT_DETAILS + sportID);
        return modelAndView;
    }

    @PostMapping("/add-sport-images/{id}")
    public ModelAndView addSportImages(@PathVariable("id") String sportID,
                                       ImageCreateBindingModel imageCreateBindingModel,
                                       ModelAndView modelAndView) throws IOException {

        SportServiceModel sportServiceModel = this.sportService.findByID(sportID);
        ImageServiceModel imageServiceModel = this.imageService
                .createImageMultipartFile(imageCreateBindingModel.getImage(), sportServiceModel.getName());

        SportServiceModel updatedSportServiceModel = this.sportService
                .addSportImage(sportServiceModel, imageServiceModel);

        modelAndView.addObject("sportServiceModel", updatedSportServiceModel);
        modelAndView.setViewName(REDIRECT_TO_SPORT_DETAILS + sportID);
        return modelAndView;
    }

//    @PostMapping("/delete-image")
//    @PreAuthorize(HAS_ROLE_ADMIN)
//    public ModelAndView deleteImage(@RequestParam(name = "sportID") String sportID,
//                                    @RequestParam(name = "imageID") String imageID,
//                                    ModelAndView modelAndView) throws Exception {
//        this.sportService.deleteImage(sportID, imageID);
//        modelAndView.setViewName(REDIRECT_TO_CREATE_SPORT_IMAGE + sportID);
//        return modelAndView;
//    }


}
