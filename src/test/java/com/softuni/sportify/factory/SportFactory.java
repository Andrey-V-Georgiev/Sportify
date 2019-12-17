package com.softuni.sportify.factory;

import com.softuni.sportify.domain.entities.Sport;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;

import java.util.ArrayList;

import static com.softuni.sportify.factory.ImageFactory.*;

public abstract class SportFactory {

    public static SportServiceModel createValidSportServiceModel() {

        SportServiceModel sportServiceModel = new SportServiceModel();

        sportServiceModel.setName("name");
        sportServiceModel.setSportDescription("1234567890");
        sportServiceModel.setIconImage(createValidImageServiceModel());
        sportServiceModel.setSportImages(new ArrayList<>());

        return sportServiceModel;
    }

    public static Sport createValidSport() {

        Sport  sport  = new Sport ();
        sport.setName("name");
        sport.setSportDescription("1234567890");
        sport.setIconImage(createValidImage());
        sport.setSportImages(new ArrayList<>());

        return sport;
    }
}
