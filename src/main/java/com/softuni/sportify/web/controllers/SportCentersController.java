package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.*;
import com.softuni.sportify.domain.models.service_models.*;
import com.softuni.sportify.domain.models.view_models.AddressViewModel;
import com.softuni.sportify.domain.models.view_models.ImageViewModel;
import com.softuni.sportify.domain.models.view_models.SportCenterViewModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.DeleteException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import com.softuni.sportify.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.AuthConstants.HAS_ROLE_ADMIN;
import static com.softuni.sportify.constants.SportCentersControllerConstants.*;

@Controller
@RequestMapping("/sport-centers")
public class SportCentersController {

    private final ImageService imageService;
    private final ModelMapper modelMapper;
    private final SportCenterService sportCenterService;
    private final AddressService addressService;
    private final SportService sportService;

    @Autowired
    public SportCentersController(ImageService imageService,
                                  ModelMapper modelMapper,
                                  SportCenterService sportCenterService,
                                  AddressService addressService,
                                  SportService sportService) {
        this.imageService = imageService;
        this.modelMapper = modelMapper;
        this.sportCenterService = sportCenterService;
        this.addressService = addressService;
        this.sportService = sportService;
    }

    @GetMapping("/create-sport-center")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView createSportCenter(ModelAndView modelAndView) {

        modelAndView.addObject("sportCenterCreateBindingModel", new SportCenterCreateBindingModel());
        modelAndView.setViewName(VIEW_CREATE_SPORT_CENTER);
        return modelAndView;
    }

    @PostMapping("/create-sport-center")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView createSportCenterConfirmed(
            @Valid
            @ModelAttribute SportCenterCreateBindingModel sportCenterCreateBindingModel,
            BindingResult sportCenterBindingResult,
            ModelAndView modelAndView) throws IOException, CreateException {

//        if (sportCenterBindingResult.hasErrors()) {
//            modelAndView.addObject("sportCenterCreateBindingModel", sportCenterCreateBindingModel);
//            modelAndView.setViewName(VIEW_CREATE_SPORT_CENTER);
//            return modelAndView;
//        }

        SportCenterServiceModel sportCenterServiceModel = this.modelMapper.map(
                sportCenterCreateBindingModel, SportCenterServiceModel.class);

        ImageServiceModel iconImageServiceModel = this.imageService.createImageMultipartFile(
                sportCenterCreateBindingModel.getIconImage(), sportCenterCreateBindingModel.getName());

        sportCenterServiceModel.setIconImage(iconImageServiceModel);

        AddressServiceModel addressServiceModel = this.addressService
                .createAddress(this.modelMapper.map(sportCenterCreateBindingModel, AddressServiceModel.class));

        sportCenterServiceModel.setAddress(addressServiceModel);

        SportCenterServiceModel newSportCenterServiceModel = this.sportCenterService
                .createSportCenter(sportCenterServiceModel);

        modelAndView.setViewName(REDIRECT_TO_SPORT_CENTER_DETAILS + newSportCenterServiceModel.getId());
        return modelAndView;
    }

    @GetMapping("/sport-center-details/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView sportCenterDetails(
            @PathVariable String id,
            ModelAndView modelAndView) throws ReadException {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(id);
        SportCenterViewModel sportCenterViewModel = this.modelMapper
                .map(sportCenterServiceModel, SportCenterViewModel.class);
        AddressEditBindingModel addressEditBindingModel = this.modelMapper
                .map(sportCenterViewModel.getAddress(), AddressEditBindingModel.class);

        modelAndView.addObject("addressEditBindingModel", addressEditBindingModel);
        modelAndView.addObject("sportCenterViewModel", sportCenterViewModel);
        modelAndView.addObject("imageCreateBindingModel", new ImageCreateBindingModel());

        modelAndView.setViewName(VIEW_SPORT_CENTER_DETAILS);
        return modelAndView;
    }

    @PostMapping("/edit-sport-center-address/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editSportCenterAddress(
            @PathVariable("id") String sportCenterID,
            @Valid
            @ModelAttribute AddressEditBindingModel addressEditBindingModel,
            BindingResult addressBindingResult,
            ModelAndView modelAndView) throws IOException, ReadException, UpdateException {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);

//        if (addressBindingResult.hasErrors()) {
//            SportCenterViewModel sportCenterViewModel = this.modelMapper
//                    .map(sportCenterServiceModel, SportCenterViewModel.class);
//            modelAndView.addObject("addressEditBindingModel", addressEditBindingModel);
//            modelAndView.addObject("sportCenterViewModel", sportCenterViewModel);
//            modelAndView.setViewName(VIEW_SPORT_CENTER_DETAILS);
//            return modelAndView;
//        }

        addressEditBindingModel.setId(sportCenterServiceModel.getAddress().getId());
        AddressServiceModel addressServiceModel = this.modelMapper.map(
                addressEditBindingModel, AddressServiceModel.class);
        sportCenterServiceModel.setAddress(this.addressService.editAddress(addressServiceModel));
        SportCenterServiceModel updatedSportCenterServiceModel = this.sportCenterService
                .editSportCenterAddress(sportCenterServiceModel);
        SportCenterViewModel sportCenterViewModel = this.modelMapper
                .map(updatedSportCenterServiceModel, SportCenterViewModel.class);

        modelAndView.addObject("sportCenterViewModel", sportCenterViewModel);

        modelAndView.setViewName(REDIRECT_TO_SPORT_CENTER_DETAILS + sportCenterID);
        return modelAndView;
    }

    @GetMapping("/guests-sport-center-details/{id}")
    public ModelAndView guestsSportCenterDetails(
            @PathVariable String id,
            ModelAndView modelAndView) throws ReadException {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(id);
        SportCenterViewModel sportCenterViewModel = this.modelMapper
                .map(sportCenterServiceModel, SportCenterViewModel.class);
        modelAndView.addObject("sportCenterViewModel", sportCenterViewModel);

        modelAndView.setViewName(VIEW_GUESTS_SPORT_CENTER_DETAILS);
        return modelAndView;
    }

    @PostMapping("/edit-description/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editDescription(
            @PathVariable("id") String sportCenterID,
            @ModelAttribute SportCenterEditBindingModel sportCenterEditBindingModel,
            ModelAndView modelAndView) throws ReadException, UpdateException {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);
        sportCenterServiceModel.setDescription(sportCenterEditBindingModel.getDescription());
        SportCenterServiceModel updatedSportCenterServiceModel = this.sportCenterService
                .updateSportCenter(sportCenterServiceModel);
        SportCenterViewModel sportCenterViewModel = this.modelMapper
                .map(updatedSportCenterServiceModel, SportCenterViewModel.class);

        modelAndView.addObject("sportCenterViewModel", sportCenterViewModel);

        modelAndView.setViewName(REDIRECT_TO_SPORT_CENTER_DETAILS + sportCenterID);
        return modelAndView;
    }

    @PostMapping("/add-sport-center-images/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView addSportCenterImages(@PathVariable("id") String sportCenterID,
                                             @Valid
                                             @ModelAttribute ImageCreateBindingModel imageCreateBindingModel,
                                             BindingResult imageBindingResult,
                                             ModelAndView modelAndView) throws IOException, ReadException, CreateException, UpdateException {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);

//        if (imageBindingResult.hasErrors()) {
//            SportCenterViewModel sportCenterViewModel = this.modelMapper
//                    .map(sportCenterServiceModel, SportCenterViewModel.class);
//            AddressEditBindingModel addressEditBindingModel = this.modelMapper
//                    .map(sportCenterServiceModel.getAddress(), AddressEditBindingModel.class);
//            modelAndView.addObject("addressEditBindingModel", addressEditBindingModel);
//            modelAndView.addObject("sportCenterViewModel", sportCenterViewModel);
//            modelAndView.addObject("imageCreateBindingModel", imageCreateBindingModel);
//
//            modelAndView.setViewName(VIEW_SPORT_CENTER_DETAILS);
//            return modelAndView;
//        }

        ImageServiceModel imageServiceModel = this.imageService
                .createImageMultipartFile(imageCreateBindingModel.getImage(), imageCreateBindingModel.getName());
        SportCenterServiceModel updatedSportCenterServiceModel = this.sportCenterService
                .addSportCenterImage(sportCenterServiceModel, imageServiceModel);
        SportCenterViewModel sportCenterViewModel = this.modelMapper
                .map(updatedSportCenterServiceModel, SportCenterViewModel.class);

        modelAndView.addObject("sportCenterViewModel", sportCenterViewModel);

        modelAndView.setViewName(REDIRECT_TO_SPORT_CENTER_DETAILS + sportCenterID);
        return modelAndView;
    }

    @GetMapping("/edit-sport-center-image/{sportCenterID}/{imageID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editSportCenterImage(
            @PathVariable("sportCenterID") String sportCenterID,
            @PathVariable("imageID") String imageID,
            ModelAndView modelAndView) throws ReadException {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);
        SportCenterViewModel sportCenterViewModel = this.modelMapper
                .map(sportCenterServiceModel, SportCenterViewModel.class);
        ImageServiceModel imageServiceModel = this.imageService.findImageByID(imageID);
        ImageViewModel imageViewModel = this.modelMapper.map(imageServiceModel, ImageViewModel.class);

        modelAndView.addObject("sportCenterViewModel", sportCenterViewModel);
        modelAndView.addObject("imageViewModel", imageViewModel);
        modelAndView.addObject("imageEditBindingModel", new ImageEditBindingModel());

        modelAndView.setViewName(VIEW_EDIT_SPORT_CENTER_IMAGE);
        return modelAndView;
    }

    @PostMapping("/edit-sport-center-image/{sportCenterID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editSportCenterImageConfirmed(
            @PathVariable("sportCenterID") String sportCenterID,
            @Valid
            @ModelAttribute ImageEditBindingModel imageEditBindingModel,
            BindingResult imageBindingResult,
            ModelAndView modelAndView) throws IOException, UpdateException {

//        if (imageBindingResult.hasErrors()) {
//            SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);
//            SportCenterViewModel sportCenterViewModel = this.modelMapper
//                    .map(sportCenterServiceModel, SportCenterViewModel.class);
//            AddressEditBindingModel addressEditBindingModel = this.modelMapper
//                    .map(sportCenterViewModel.getAddress(), AddressEditBindingModel.class);
//            ImageViewModel imageViewModel = this.modelMapper.map(imageEditBindingModel, ImageViewModel.class);
//            modelAndView.addObject("addressEditBindingModel", addressEditBindingModel);
//            modelAndView.addObject("sportCenterViewModel", sportCenterViewModel);
//            modelAndView.addObject("imageViewModel", imageViewModel);
//            modelAndView.addObject("imageEditBindingModel", imageEditBindingModel);
//            modelAndView.setViewName(VIEW_EDIT_SPORT_CENTER_IMAGE);
//            return modelAndView;
//        }

        this.imageService.editImage(this.modelMapper.map(imageEditBindingModel, ImageServiceModel.class));

        modelAndView.setViewName(REDIRECT_TO_SPORT_CENTER_DETAILS + sportCenterID);
        return modelAndView;
    }

    @PostMapping("/delete-sport-center-image/{sportCenterID}/{imageID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView deleteImage(
            @PathVariable("sportCenterID") String sportCenterID,
            @PathVariable("imageID") String imageID,
            ModelAndView modelAndView) throws Exception, DeleteException {

        this.sportCenterService.deleteSportCenterImage(sportCenterID, imageID);
        this.imageService.deleteImage(imageID);

        modelAndView.setViewName(REDIRECT_TO_SPORT_CENTER_DETAILS + sportCenterID);
        return modelAndView;
    }

    @GetMapping("/show-all-sport-centers")
    public ModelAndView showAllSports(ModelAndView modelAndView) {

        List<SportCenterViewModel> sportCenterViewModels = this.sportCenterService.findAllSportCenters()
                .stream()
                .map(sc -> this.modelMapper.map(sc, SportCenterViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("sportCenterViewModels", sportCenterViewModels);

        modelAndView.setViewName(VIEW_SHOW_ALL_SPORT_CENTERS);
        return modelAndView;
    }

    @GetMapping("/update-sport-center-sports/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView updateSportCenterSports(
            @PathVariable("id") String sportCenterID,
            @ModelAttribute SportCenterUpdateSportsBindingModel bindingModel,
            ModelAndView modelAndView) throws IOException {

        List<SportCenterViewModel> sportCenterViewModels = this.sportService.findAllSports()
                .stream()
                .map(sc -> this.modelMapper.map(sc, SportCenterViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("sportCenterViewModels", sportCenterViewModels);
        modelAndView.addObject("sportCenterID", sportCenterID);

        modelAndView.setViewName(VIEW_UPDATE_SPORT_CENTER_SPORTS);
        return modelAndView;
    }

    @PostMapping("/update-sport-center-sports/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView updateSportCenterSportsConfirm(
            @PathVariable("id") String sportCenterID,
            @ModelAttribute SportCenterUpdateSportsBindingModel bindingModel,
            ModelAndView modelAndView) throws IOException, ReadException, UpdateException {

        List<String> spotrsIDs = bindingModel.getSpotrsIDs();
        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);
        this.sportCenterService.updateSportCenterSports(sportCenterServiceModel, spotrsIDs);

        modelAndView.setViewName(REDIRECT_TO_SPORT_CENTER_DETAILS + sportCenterID);
        return modelAndView;
    }

    @PostMapping("/delete-sport-center/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView deleteSportCenter(
            @PathVariable("id") String sportCenterID,
            ModelAndView modelAndView) throws IOException, ReadException, UpdateException, DeleteException {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);
        this.sportCenterService.deleteSportCenter(sportCenterServiceModel);

        modelAndView.setViewName(REDIRECT_TO_SHOW_ALL_SPORT_CENTERS);
        return modelAndView;
    }
}
