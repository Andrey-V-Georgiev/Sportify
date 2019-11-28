package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface SportService {

    SportServiceModel createSport(SportServiceModel sportServiceModel,
                                  ImageServiceModel descriptionImageServiceModel,
                                  ImageServiceModel iconImageServiceModel);

    SportServiceModel findByID(String id);

    SportServiceModel updateSportDescription(SportServiceModel sportServiceModel);

    SportServiceModel addSportImage(SportServiceModel sportServiceModel, ImageServiceModel imageServiceModel);


//    void deleteImage(String sportID, String imageID) throws Exception;
}
