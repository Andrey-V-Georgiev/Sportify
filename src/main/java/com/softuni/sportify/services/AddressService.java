package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.AddressServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.UpdateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    AddressServiceModel createAddress(AddressServiceModel addressServiceModel) throws CreateException;

    AddressServiceModel editAddress(AddressServiceModel addressServiceModel) throws UpdateException;

    List<AddressServiceModel> findAll();
}
