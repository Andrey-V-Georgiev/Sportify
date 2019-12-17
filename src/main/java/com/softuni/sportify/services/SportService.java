package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.DeleteException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SportService {

    SportServiceModel createSport(SportServiceModel sportServiceModel,
                                  ImageServiceModel iconImageServiceModel) throws CreateException;

    SportServiceModel findByID(String id) throws ReadException;

    SportServiceModel findByName(String name) throws ReadException;

    SportServiceModel updateSportDescription(SportServiceModel sportServiceModel) throws UpdateException;

    SportServiceModel addSportImage(SportServiceModel sportServiceModel,
                                    ImageServiceModel imageServiceModel) throws UpdateException;

    List<SportServiceModel> findAllSports();

    void deleteSportImage(String sportID, String imageID) throws UpdateException;

    List<String> findAllSportsNames();

    List<String> findAllSportsNamesStartsWith(String id);

    void deleteSport(SportServiceModel sportServiceModel) throws DeleteException, UpdateException;

}
