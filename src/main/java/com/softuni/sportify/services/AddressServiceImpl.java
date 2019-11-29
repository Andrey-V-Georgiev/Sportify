package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Address;
import com.softuni.sportify.domain.models.service_models.AddressServiceModel;
import com.softuni.sportify.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository,
                              ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressServiceModel createAddress(AddressServiceModel addressServiceModel) {

        Address address = this.modelMapper.map(addressServiceModel, Address.class);
        Address newAddress = null;
        try {
            newAddress = this.addressRepository.saveAndFlush(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.modelMapper.map(newAddress, AddressServiceModel.class);
    }

    @Override
    public AddressServiceModel editAddress(AddressServiceModel addressServiceModel) {

        Address address = this.modelMapper.map(addressServiceModel, Address.class);
        Address updatedAddress = this.addressRepository.saveAndFlush(address);
        return this.modelMapper.map(updatedAddress, AddressServiceModel.class);
    }
}
