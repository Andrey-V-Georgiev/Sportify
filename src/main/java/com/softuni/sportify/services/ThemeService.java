package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.ThemeServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ThemeService {

    ThemeServiceModel createNewTheme(ThemeServiceModel themeServiceModel, ImageServiceModel imageServiceModel);

    List<ThemeServiceModel> findAll();

    ThemeServiceModel findByID(String id);

    void addIndexCarouselImage(ThemeServiceModel themeServiceModel, ImageServiceModel imageServiceModel);

    void addHomeCarouselImage(ThemeServiceModel themeServiceModel, ImageServiceModel imageServiceModel);

    void addAdminPanelImages(ThemeServiceModel themeServiceModel, ImageServiceModel imageServiceModel);

    void deleteThemeImage(String themeID, String imageID);

    void deleteTheme(String id);

    void deleteAdminPanelImages(ThemeServiceModel themeServiceModel);
}


