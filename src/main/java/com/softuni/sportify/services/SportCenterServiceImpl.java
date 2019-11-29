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
}
