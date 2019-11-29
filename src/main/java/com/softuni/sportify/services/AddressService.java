package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.AddressServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {
    AddressServiceModel createAddress(AddressServiceModel addressServiceModel);

    AddressServiceModel editAddress(AddressServiceModel addressServiceModel);
}
