package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SportCenterService {

    SportCenterServiceModel createSportCenter(SportCenterServiceModel sportCenterServiceModel,
                                              ImageServiceModel iconImageServiceModel);

    SportCenterServiceModel findByID(String id);

    SportCenterServiceModel updateSportCenterDescription(SportCenterServiceModel sportCenterServiceModel);

    SportCenterServiceModel addSportCenterImage(SportCenterServiceModel sportCenterServiceModel,
                                                ImageServiceModel imageServiceModel);

    SportCenterServiceModel editSportCenterAddress(SportCenterServiceModel sportCenterServiceModel);

    List<SportCenterServiceModel> findAllSportCenters();

    SportCenterServiceModel updateSportCenterSports(SportCenterServiceModel sportCenterServiceModel,
                                                    List<String> sportCenterIDs);

    void deleteSportCenterImage(String sportCenterID, String imageID);
}
