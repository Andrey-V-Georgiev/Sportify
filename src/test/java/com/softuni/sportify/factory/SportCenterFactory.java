package com.softuni.sportify.factory;

import com.softuni.sportify.domain.entities.SportCenter;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;

import java.util.ArrayList;

import static com.softuni.sportify.factory.AddressFactory.createValidAddress;
import static com.softuni.sportify.factory.AddressFactory.createValidAddressServiceModel;
import static com.softuni.sportify.factory.ImageFactory.createValidImage;
import static com.softuni.sportify.factory.ImageFactory.createValidImageServiceModel;

public abstract class SportCenterFactory {

    public static SportCenterServiceModel createValidSportCenterServiceModel() {

        SportCenterServiceModel sportCenterServiceModel = new SportCenterServiceModel();
        sportCenterServiceModel.setName("name");
        sportCenterServiceModel.setDescription("1234567890");
        sportCenterServiceModel.setSportCenterImages(new ArrayList<>());
        sportCenterServiceModel.setCalendar(new ArrayList<>());
        sportCenterServiceModel.setSports(new ArrayList<>());
        sportCenterServiceModel.setAddress(createValidAddressServiceModel());
        sportCenterServiceModel.setIconImage(createValidImageServiceModel());

        return sportCenterServiceModel;
    }

    public static SportCenter createValidSportCenter() {

        SportCenter sportCenter = new SportCenter();
        sportCenter.setName("name");
        sportCenter.setDescription("1234567890");
        sportCenter.setSportCenterImages(new ArrayList<>());
        sportCenter.setCalendar(new ArrayList<>());
        sportCenter.setSports(new ArrayList<>());
        sportCenter.setAddress(createValidAddress());
        sportCenter.setIconImage(createValidImage());

        return sportCenter;
    }
}
