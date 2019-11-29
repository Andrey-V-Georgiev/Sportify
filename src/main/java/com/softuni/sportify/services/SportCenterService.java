package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface SportCenterService {

    SportCenterServiceModel createSportCenter(SportCenterServiceModel sportCenterServiceModel,
                                              ImageServiceModel descriptionImageServiceModel,
                                              ImageServiceModel iconImageServiceModel);

}
