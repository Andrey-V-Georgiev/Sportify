package com.softuni.sportify.factory;

import com.softuni.sportify.domain.entities.Address;
import com.softuni.sportify.domain.models.service_models.AddressServiceModel;

public abstract class AddressFactory {

    public static AddressServiceModel createValidAddressServiceModel() {

        AddressServiceModel addressServiceModel = new AddressServiceModel();
        addressServiceModel.setCountry("country");
        addressServiceModel.setCity("city");
        addressServiceModel.setStreet("street");
        addressServiceModel.setDetails("1234567890");
        return addressServiceModel;
    }

    public static Address createValidAddress () {

        Address address = new Address();
        address.setCountry("country");
        address.setCity("city");
        address.setStreet("street");
        address.setDetails("1234567890");
        return address;
    }
}
