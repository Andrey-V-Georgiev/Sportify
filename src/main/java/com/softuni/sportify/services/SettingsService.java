package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SettingServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SettingsService {

    SettingServiceModel createNewSetting(SettingServiceModel settingServiceModel, ImageServiceModel imageServiceModel);

    List<SettingServiceModel> findAll();

    SettingServiceModel findByID(String id);

    void addIndexCarouselImage(SettingServiceModel settingServiceModel, ImageServiceModel imageServiceModel);

    void addHomeCarouselImage(SettingServiceModel settingServiceModel, ImageServiceModel imageServiceModel);

    void addAdminPanelImages(SettingServiceModel settingServiceModel, ImageServiceModel imageServiceModel);

    void deleteSettingImage(String settingID, String imageID);

    void deleteSetting(String id);
}


