package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.*;
import com.softuni.sportify.domain.models.service_models.*;
import com.softuni.sportify.exceptions.*;
import com.softuni.sportify.repositories.ImageRepository;
import com.softuni.sportify.repositories.SportCenterRepository;
import com.softuni.sportify.repositories.SportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.ExceptionConstants.*;

@Service
public class SportCenterServiceImpl implements SportCenterService {

    private final ModelMapper modelMapper;
    private final SportCenterRepository sportCenterRepository;
    private final SportRepository sportRepository;
    private final ImageRepository imageRepository;
    private final Validator validator;

    @Autowired
    public SportCenterServiceImpl(ModelMapper modelMapper,
                                  SportCenterRepository sportCenterRepository,
                                  SportRepository sportRepository,
                                  ImageRepository imageRepository,
                                  Validator validator) {
        this.modelMapper = modelMapper;
        this.sportCenterRepository = sportCenterRepository;
        this.sportRepository = sportRepository;
        this.imageRepository = imageRepository;
        this.validator = validator;
    }

    @Override
    public SportCenterServiceModel createSportCenter(SportCenterServiceModel sportCenterServiceModel)
            throws CreateException {

        if (!validator.validate(sportCenterServiceModel).isEmpty()) {
            throw new CreateException(SPORT_CENTER_CREATE_EXCEPTION_MSG);
        }
        SportCenter sportCenter = this.modelMapper.map(sportCenterServiceModel, SportCenter.class);
        SportCenter newSportCenter = this.sportCenterRepository.saveAndFlush(sportCenter);
        return this.modelMapper.map(newSportCenter, SportCenterServiceModel.class);
    }

    @Override
    public SportCenterServiceModel findByID(String id) throws ReadException {

        SportCenter sportCenter = this.sportCenterRepository.findById(id).orElse(null);
        if (sportCenter == null) {
            throw new ReadException(SPORT_CENTER_READ_EXCEPTION_MSG);
        }
        SportCenterServiceModel sportCenterServiceModel = this.modelMapper
                .map(sportCenter, SportCenterServiceModel.class);
        if (!validator.validate(sportCenterServiceModel).isEmpty()) {
            throw new ReadException(SPORT_CENTER_READ_EXCEPTION_MSG);
        }
        return sportCenterServiceModel;
    }

    @Override
    public List<SportCenterServiceModel> findAllSportCenters() {
        return this.sportCenterRepository.findAll()
                .stream()
                .map(sc -> this.modelMapper.map(sc, SportCenterServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public SportCenterServiceModel editSportCenterAddress(SportCenterServiceModel sportCenterServiceModel) throws UpdateException {

        if(!validator.validate(sportCenterServiceModel).isEmpty()) {
            throw new UpdateException(SPORT_CENTER_UPDATE_EXCEPTION_MSG);
        }
        SportCenter sportCenter = this.modelMapper.map(sportCenterServiceModel, SportCenter.class);
        SportCenter updatedSportCenter = this.sportCenterRepository.saveAndFlush(sportCenter);
        return this.modelMapper.map(updatedSportCenter, SportCenterServiceModel.class);
    }

    @Override
    public SportCenterServiceModel addSportCenterImage(SportCenterServiceModel sportCenterServiceModel,
                                                       ImageServiceModel imageServiceModel) throws UpdateException {

        if (!validator.validate(sportCenterServiceModel).isEmpty() ||
                !validator.validate(imageServiceModel).isEmpty()) {
            throw new UpdateException(SPORT_CENTER_UPDATE_EXCEPTION_MSG);
        }
        SportCenter sportCenter = this.modelMapper.map(sportCenterServiceModel, SportCenter.class);
        Image image = this.modelMapper.map(imageServiceModel, Image.class);
        sportCenter.getSportCenterImages().add(image);
        SportCenter updatedSportCenter = this.sportCenterRepository.saveAndFlush(sportCenter);
        return this.modelMapper.map(updatedSportCenter, SportCenterServiceModel.class);
    }

    @Override
    public SportCenterServiceModel updateSportCenter(SportCenterServiceModel sportCenterServiceModel) throws UpdateException {

        if (!validator.validate(sportCenterServiceModel).isEmpty()) {
            throw new UpdateException(SPORT_CENTER_UPDATE_EXCEPTION_MSG);
        }
        SportCenter sportCenter = this.modelMapper.map(sportCenterServiceModel, SportCenter.class);
        SportCenter updatedSportCenter = this.sportCenterRepository.saveAndFlush(sportCenter);
        return this.modelMapper.map(updatedSportCenter, SportCenterServiceModel.class);
    }

    @Override
    public SportCenterServiceModel updateSportCenterSports(SportCenterServiceModel sportCenterServiceModel,
                                                           List<String> sportCenterIDs) throws UpdateException {

        if(!validator.validate(sportCenterServiceModel).isEmpty()) {
            throw new UpdateException(SPORT_CENTER_UPDATE_EXCEPTION_MSG);
        }
        List<Sport> chosenSports = new ArrayList<>();
        for (String id : sportCenterIDs) {
            chosenSports.add(this.sportRepository.findById(id)
                    .orElseThrow(()-> new UpdateException(SPORT_CENTER_UPDATE_EXCEPTION_MSG)));
        }
        SportCenter sportCenter = this.sportCenterRepository.findById(
                sportCenterServiceModel.getId()).orElse(null);
        sportCenter.setSports(chosenSports);
        SportCenter updatedSportCenter = this.sportCenterRepository.saveAndFlush(sportCenter);
        return this.modelMapper.map(updatedSportCenter, SportCenterServiceModel.class);
    }

    @Override
    public void removeCurrentSport(SportServiceModel sportServiceModel) throws UpdateException {

        if(!validator.validate(sportServiceModel).isEmpty()) {
            throw new UpdateException(SPORT_CENTER_UPDATE_EXCEPTION_MSG);
        }
        List<SportCenter> allSportCenters = this.sportCenterRepository.findAll();
        for (SportCenter sc : allSportCenters) {
            List<Sport> filteredSports = sc.getSports()
                    .stream()
                    .filter(s -> !s.getId().equals(sportServiceModel.getId()))
                    .collect(Collectors.toList());
            sc.setSports(filteredSports);
            this.sportCenterRepository.save(sc);
        }
    }

    @Override
    public void deleteSportCenterImage(String sportCenterID, String imageID) throws DeleteException {

        SportCenter sportCenter = this.sportCenterRepository.findById(sportCenterID)
                .orElseThrow(()-> new DeleteException(SPORT_CENTER_DELETE_EXCEPTION_MSG));
        Image image = this.imageRepository.findById(imageID)
                .orElseThrow(()-> new DeleteException(SPORT_CENTER_DELETE_EXCEPTION_MSG));
        List<Image> sportCenterImages = sportCenter.getSportCenterImages()
                .stream()
                .filter(i -> !i.getId().equals(image.getId()))
                .collect(Collectors.toList());
        sportCenter.setSportCenterImages(sportCenterImages);
        this.sportCenterRepository.save(sportCenter);
    }

    @Override
    public void deleteSportCenter(SportCenterServiceModel sportCenterServiceModel)
            throws DeleteException, UpdateException {

        if(!validator.validate(sportCenterServiceModel).isEmpty()) {
            throw new DeleteException(SPORT_CENTER_DELETE_EXCEPTION_MSG);
        }
        sportCenterServiceModel.setCalendar(new ArrayList<>());
        sportCenterServiceModel.setSports(new ArrayList<>());
        SportCenterServiceModel updatedSportCenterServiceModel = this.updateSportCenter(sportCenterServiceModel);
        this.sportCenterRepository.delete(this.modelMapper.map(updatedSportCenterServiceModel, SportCenter.class));
    }
}
