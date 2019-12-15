package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.ThemeServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.DeleteException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ThemeService {

    ThemeServiceModel createNewTheme(ThemeServiceModel themeServiceModel, ImageServiceModel imageServiceModel) throws CreateException;

    List<ThemeServiceModel> findAll();

    ThemeServiceModel findByID(String id) throws ReadException;

    void addIndexCarouselImage(ThemeServiceModel themeServiceModel, ImageServiceModel imageServiceModel) throws UpdateException;

    void addHomeCarouselImage(ThemeServiceModel themeServiceModel, ImageServiceModel imageServiceModel) throws UpdateException;

    void addAdminPanelImages(ThemeServiceModel themeServiceModel, ImageServiceModel imageServiceModel) throws UpdateException;

    void deleteThemeImage(String themeID, String imageID) throws UpdateException;

    void deleteTheme(String id) throws DeleteException;

    void deleteAdminPanelImages(ThemeServiceModel themeServiceModel) throws UpdateException;


    ThemeServiceModel activateTheme(ThemeServiceModel themeServiceModel) throws UpdateException, ReadException;

    ThemeServiceModel findTheActiveTheme();
}


