package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.DeleteException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SportCenterService {

    SportCenterServiceModel createSportCenter(SportCenterServiceModel sportCenterServiceModel) throws CreateException;

    SportCenterServiceModel findByID(String id) throws ReadException;

    SportCenterServiceModel updateSportCenter(SportCenterServiceModel sportCenterServiceModel) throws UpdateException;

    SportCenterServiceModel addSportCenterImage(SportCenterServiceModel sportCenterServiceModel,
                                                ImageServiceModel imageServiceModel) throws UpdateException;

    SportCenterServiceModel editSportCenterAddress(SportCenterServiceModel sportCenterServiceModel) throws UpdateException;

    List<SportCenterServiceModel> findAllSportCenters();

    SportCenterServiceModel updateSportCenterSports(SportCenterServiceModel sportCenterServiceModel,
                                                    List<String> sportCenterIDs) throws UpdateException;

    void deleteSportCenterImage(String sportCenterID, String imageID) throws DeleteException;

    void deleteSportCenter(SportCenterServiceModel sportCenterServiceModel) throws DeleteException, UpdateException;

    void removeCurrentSport(SportServiceModel sportServiceModel) throws UpdateException;
}
