package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface SportService {

    SportServiceModel addNewSport(SportServiceModel sportServiceModel);

    SportServiceModel findByID(String id);

    SportServiceModel addSportImage(String id, ImageServiceModel imageServiceModel);

//    void deleteImage(String sportID, String imageID) throws Exception;
}
