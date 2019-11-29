package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Address;
import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.entities.SportCenter;
import com.softuni.sportify.domain.models.service_models.AddressServiceModel;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import com.softuni.sportify.repositories.SportCenterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SportCenterServiceImpl implements SportCenterService {

    private final ModelMapper modelMapper;
    private final SportCenterRepository sportCenterRepository;
    private final AddressService addressService;

    @Autowired
    public SportCenterServiceImpl(ModelMapper modelMapper,
                                  SportCenterRepository sportCenterRepository,
                                  AddressService addressService) {
        this.modelMapper = modelMapper;
        this.sportCenterRepository = sportCenterRepository;
        this.addressService = addressService;
    }


    @Override
    public SportCenterServiceModel createSportCenter(SportCenterServiceModel sportCenterServiceModel,
                                                     ImageServiceModel descriptionImageServiceModel,
                                                     ImageServiceModel iconImageServiceModel) {

        SportCenter sportCenter = this.modelMapper.map(sportCenterServiceModel, SportCenter.class);
        AddressServiceModel addressServiceModel = this.addressService.createAddress(
                this.modelMapper.map(sportCenterServiceModel.getAddress(), AddressServiceModel.class));

        sportCenter.setAddress(this.modelMapper.map(addressServiceModel, Address.class));
        sportCenter.setDescriptionImage(this.modelMapper.map(descriptionImageServiceModel, Image.class));
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
                .map(sc-> this.modelMapper.map(sc, SportCenterServiceModel.class))
                .collect(Collectors.toList());
    }
}
