package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.entities.Sport;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.exceptions.*;
import com.softuni.sportify.repositories.ImageRepository;
import com.softuni.sportify.repositories.SportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.ExceptionConstants.*;

@Service
public class SportServiceImpl implements SportService {

    private final ModelMapper modelMapper;
    private final SportRepository sportRepository;
    private final ImageRepository imageRepository;
    private final SportCenterService sportCenterService;
    private final EventService eventService;
    private final Validator validator;

    @Autowired
    public SportServiceImpl(ModelMapper modelMapper,
                            SportRepository sportRepository,
                            ImageRepository imageRepository,
                            SportCenterService sportCenterService,
                            EventService eventService,
                            Validator validator) {
        this.modelMapper = modelMapper;
        this.sportRepository = sportRepository;
        this.imageRepository = imageRepository;
        this.sportCenterService = sportCenterService;
        this.eventService = eventService;
        this.validator = validator;
    }

    @Override
    public SportServiceModel createSport(SportServiceModel sportServiceModel,
                                         ImageServiceModel iconImageServiceModel) throws CreateException {

        sportServiceModel.setSportDescription("");
        sportServiceModel.setIconImage(iconImageServiceModel);
        Set<ConstraintViolation<SportServiceModel>> validate = validator.validate(sportServiceModel);
        if(!validate.isEmpty()) {
            throw new CreateException(SPORT_CREATE_EXCEPTION_MSG);
        }
        Sport sport = this.modelMapper.map(sportServiceModel, Sport.class);
        Sport newSport = this.sportRepository.saveAndFlush(sport);
        return this.modelMapper.map(newSport, SportServiceModel.class);
    }

    @Override
    public SportServiceModel findByID(String id) throws ReadException {

        Sport sport = this.sportRepository.findById(id)
                .orElseThrow(()-> new ReadException(SPORT_READ_EXCEPTION_MSG));
        return this.modelMapper.map(sport, SportServiceModel.class);
    }

    @Override
    public SportServiceModel findByName(String name) throws ReadException {

        Sport sport = null;
        try {
            sport = this.sportRepository.findByName(name);
        } catch (Exception e) {
            throw new ReadException(SPORT_READ_EXCEPTION_MSG);
        }
        return this.modelMapper.map(sport, SportServiceModel.class);
    }

    @Override
    public List<SportServiceModel> findAllSports() {

        return this.sportRepository.findAll()
                .stream()
                .map(s -> this.modelMapper.map(s, SportServiceModel.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<String> findAllSportsNames() {

        return this.sportRepository.findAll()
                .stream()
                .map(Sport::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllSportsNamesStartsWith(String id) throws ReadException {

        this.sportRepository.findById(id)
                .orElseThrow(() -> new ReadException(SPORT_READ_EXCEPTION_MSG));

        List<SportServiceModel> allSportServiceModels = this.findAllSports();
        Comparator<SportServiceModel> startsWithSport = Comparator
                .comparing(s -> !s.getId().equals(id));
        List<String> sortedSportsNames = allSportServiceModels
                .stream()
                .sorted(startsWithSport)
                .map(s -> s.getName())
                .collect(Collectors.toList());

        return sortedSportsNames;
    }


    @Override
    public SportServiceModel addSportImage(SportServiceModel sportServiceModel,
                                           ImageServiceModel imageServiceModel) throws UpdateException {

        Set<ConstraintViolation<SportServiceModel>> validateSportServiceModel = validator.validate(sportServiceModel);
        Set<ConstraintViolation<ImageServiceModel>> validateImageServiceModel = validator.validate(imageServiceModel);
        if(!validateSportServiceModel.isEmpty() || !validateImageServiceModel.isEmpty()) {
            throw new UpdateException(SPORT_UPDATE_EXCEPTION_MSG);
        }
        Sport sport = this.modelMapper.map(sportServiceModel, Sport.class);
        Image image = this.modelMapper.map(imageServiceModel, Image.class);
        sport.getSportImages().add(image);
        Sport updatedSport = this.sportRepository.saveAndFlush(sport);
        return this.modelMapper.map(updatedSport, SportServiceModel.class);
    }

    @Override
    public SportServiceModel updateSportDescription(SportServiceModel sportServiceModel) throws UpdateException {

        if(!validator.validate(sportServiceModel).isEmpty()) {
            throw new UpdateException(SPORT_UPDATE_EXCEPTION_MSG);
        }
        Sport sport = this.modelMapper.map(sportServiceModel, Sport.class);
        Sport updatedSport = this.sportRepository.saveAndFlush(sport);
        return this.modelMapper.map(updatedSport, SportServiceModel.class);
    }

    @Override
    public void deleteSportImage(String sportID, String imageID) throws UpdateException {

        Sport sport = this.sportRepository.findById(sportID)
                .orElseThrow(()-> new UpdateException(SPORT_UPDATE_EXCEPTION_MSG));
        Image image = this.imageRepository.findById(imageID)
                .orElseThrow(()-> new UpdateException(SPORT_UPDATE_EXCEPTION_MSG));

        List<Image> sportImages = sport.getSportImages()
                .stream()
                .filter(i -> !i.getId().equals(image.getId()))
                .collect(Collectors.toList());
        sport.setSportImages(sportImages);

        this.sportRepository.save(sport);
    }

    @Override
    public void deleteSport(SportServiceModel sportServiceModel) throws DeleteException, UpdateException {

        if(!validator.validate(sportServiceModel).isEmpty()) {
            throw new DeleteException(SPORT_DELETE_EXCEPTION_MSG);
        }
        this.sportCenterService.removeCurrentSport(sportServiceModel);
        this.eventService.deleteAllBySport(sportServiceModel);
        sportServiceModel.setSportImages(new ArrayList<>());
        this.sportRepository.delete(this.modelMapper.map(sportServiceModel, Sport.class));
    }

}
