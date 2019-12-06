package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SportService {

    SportServiceModel createSport(SportServiceModel sportServiceModel,
                                  ImageServiceModel iconImageServiceModel);

    SportServiceModel findByID(String id);

    SportServiceModel findByName(String name);

    SportServiceModel updateSportDescription(SportServiceModel sportServiceModel);

    SportServiceModel addSportImage(SportServiceModel sportServiceModel,
                                    ImageServiceModel imageServiceModel);

    void deleteSport(String id);

    List<SportServiceModel> findAllSports();

    void deleteSportImage(String sportID, String imageID);

    SportServiceModel editIconImage(SportServiceModel sportServiceModel);

    List<String> findAllSportsNames();



}
