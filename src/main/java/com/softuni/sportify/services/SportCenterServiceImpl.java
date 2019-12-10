package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.*;
import com.softuni.sportify.domain.models.service_models.AddressServiceModel;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import com.softuni.sportify.repositories.ImageRepository;
import com.softuni.sportify.repositories.ScheduleRepository;
import com.softuni.sportify.repositories.SportCenterRepository;
import com.softuni.sportify.repositories.SportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SportCenterServiceImpl implements SportCenterService {

    private final ModelMapper modelMapper;
    private final SportCenterRepository sportCenterRepository;
    private final AddressService addressService;
    private final SportRepository sportRepository;
    private final ImageRepository imageRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleService scheduleService;

    @Autowired
    public SportCenterServiceImpl(ModelMapper modelMapper,
                                  SportCenterRepository sportCenterRepository,
                                  AddressService addressService,
                                  SportRepository sportRepository,
                                  ImageRepository imageRepository,
                                  ScheduleRepository scheduleRepository,
                                  ScheduleService scheduleService) {
        this.modelMapper = modelMapper;
        this.sportCenterRepository = sportCenterRepository;
        this.addressService = addressService;
        this.sportRepository = sportRepository;
        this.imageRepository = imageRepository;
        this.scheduleRepository = scheduleRepository;
        this.scheduleService = scheduleService;
    }

    @Override
    public SportCenterServiceModel createSportCenter(SportCenterServiceModel sportCenterServiceModel,
                                                     ImageServiceModel iconImageServiceModel) {

        SportCenter sportCenter = this.modelMapper.map(sportCenterServiceModel, SportCenter.class);
        AddressServiceModel addressServiceModel = this.addressService.createAddress(
                this.modelMapper.map(sportCenterServiceModel.getAddress(), AddressServiceModel.class));

        sportCenter.setAddress(this.modelMapper.map(addressServiceModel, Address.class));
        sportCenter.setIconImage(this.modelMapper.map(iconImageServiceModel, Image.class));

        SportCenter newSportCenter = null;
        try {
            newSportCenter = this.sportCenterRepository.saveAndFlush(sportCenter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.modelMapper.map(newSportCenter, SportCenterServiceModel.class);
    }

    @Override
    public SportCenterServiceModel findByID(String id) {

        SportCenter sportCenter = this.sportCenterRepository.findById(id).orElse(null);
        return this.modelMapper.map(sportCenter, SportCenterServiceModel.class);
    }

    @Override
    public SportCenterServiceModel updateSportCenterDescription(SportCenterServiceModel sportCenterServiceModel) {

        SportCenter sportCenter = this.modelMapper.map(sportCenterServiceModel, SportCenter.class);
        SportCenter updatedSportCenter = null;
        try {
            updatedSportCenter = this.sportCenterRepository.saveAndFlush(sportCenter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.modelMapper.map(updatedSportCenter, SportCenterServiceModel.class);
    }

    @Override
    public SportCenterServiceModel addSportCenterImage(SportCenterServiceModel sportCenterServiceModel,
                                                       ImageServiceModel imageServiceModel) {

        SportCenter sportCenter = this.modelMapper.map(sportCenterServiceModel, SportCenter.class);
        Image image = this.modelMapper.map(imageServiceModel, Image.class);
        sportCenter.getSportCenterImages().add(image);
        SportCenter updatedSportCenter = this.sportCenterRepository.saveAndFlush(sportCenter);
        return this.modelMapper.map(updatedSportCenter, SportCenterServiceModel.class);
    }

    @Override
    public SportCenterServiceModel editSportCenterAddress(SportCenterServiceModel sportCenterServiceModel) {

        SportCenter sportCenter = this.modelMapper.map(sportCenterServiceModel, SportCenter.class);
        SportCenter updatedSportCenter = null;
        try {
            updatedSportCenter = this.sportCenterRepository.saveAndFlush(sportCenter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.modelMapper.map(updatedSportCenter, SportCenterServiceModel.class);
    }

    @Override
    public List<SportCenterServiceModel> findAllSportCenters() {
        return this.sportCenterRepository.findAll()
                .stream()
                .map(sc -> this.modelMapper.map(sc, SportCenterServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public SportCenterServiceModel updateSportCenterSports(SportCenterServiceModel sportCenterServiceModel,
                                                           List<String> sportCenterIDs) {
        List<Sport> chosenSports = new ArrayList<>();
        for (String id : sportCenterIDs) {
            chosenSports.add(this.sportRepository.findById(id).orElse(null));
        }
        SportCenter sportCenter = this.sportCenterRepository.findById(
                sportCenterServiceModel.getId()).orElse(null);
        sportCenter.setSports(chosenSports);
        SportCenter updatedSportCenter = this.sportCenterRepository.saveAndFlush(sportCenter);

        return this.modelMapper.map(updatedSportCenter, SportCenterServiceModel.class);
    }

    @Override
    public void deleteSportCenterImage(String sportCenterID, String imageID) {

        SportCenter sportCenter = this.sportCenterRepository.findById(sportCenterID).orElse(null);
        Image image = this.imageRepository.findById(imageID).orElse(null);
        List<Image> sportCenterImages = sportCenter.getSportCenterImages()
                .stream()
                .filter(i -> !i.getId().equals(image.getId()))
                .collect(Collectors.toList());
        sportCenter.setSportCenterImages(sportCenterImages);

        this.sportCenterRepository.save(sportCenter);
    }

    @Override
    public void deleteSportCenter(SportCenterServiceModel sportCenterServiceModel) {

        SportCenter sportCenter = this.modelMapper.map(sportCenterServiceModel, SportCenter.class);
        Schedule schedule = this.scheduleRepository.findAll()
                .stream()
                .filter(s -> s.getSportCenter().getId().equals(sportCenter.getId()))
                .collect(Collectors.toList())
                .get(0);
        this.scheduleService.deleteSchedule(this.modelMapper.map(schedule, ScheduleServiceModel.class));
        this.sportCenterRepository.delete(sportCenter);
    }


}
