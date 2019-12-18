package com.softuni.sportify.factory;

import com.softuni.sportify.domain.entities.Theme;
import com.softuni.sportify.domain.models.service_models.ThemeServiceModel;

import java.util.ArrayList;

import static com.softuni.sportify.factory.ImageFactory.createValidImage;
import static com.softuni.sportify.factory.ImageFactory.createValidImageServiceModel;

public abstract class ThemeFactory {

    public static ThemeServiceModel createValidThemeServiceModel() {

        ThemeServiceModel themeServiceModel = new ThemeServiceModel();

        themeServiceModel.setName("defaultName");
        themeServiceModel.setIconImage(createValidImageServiceModel());
        themeServiceModel.setIndexCarouselImages(new ArrayList<>());
        themeServiceModel.setHomeCarouselImages(new ArrayList<>());
        themeServiceModel.setAdminPanelImages(new ArrayList<>());
        themeServiceModel.setActive(false);

        return themeServiceModel;
    }

    public static Theme createValidTheme() {

        Theme theme = new Theme();

        theme.setName("defaultName");
        theme.setIconImage(createValidImage());
        theme.setIndexCarouselImages(new ArrayList<>());
        theme.setHomeCarouselImages(new ArrayList<>());
        theme.setAdminPanelImages(new ArrayList<>());
        theme.setActive(false);

        return theme;
    }
}
