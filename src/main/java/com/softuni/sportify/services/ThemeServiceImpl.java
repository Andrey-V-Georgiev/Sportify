package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.entities.Theme;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.ThemeServiceModel;
import com.softuni.sportify.repositories.ImageRepository;
import com.softuni.sportify.repositories.ThemeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;

    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository,
                            ModelMapper modelMapper,
                            ImageRepository imageRepository) {
        this.themeRepository = themeRepository;
        this.modelMapper = modelMapper;
        this.imageRepository = imageRepository;
    }

    @Override
    public ThemeServiceModel createNewTheme(ThemeServiceModel themeServiceModel,
                                            ImageServiceModel imageServiceModel) {

        Theme theme = this.modelMapper.map(themeServiceModel, Theme.class);
        Image image = this.modelMapper.map(imageServiceModel, Image.class);
        theme.setIconImage(image);
        theme.setActive(false);
        Theme savedTheme = this.themeRepository.saveAndFlush(theme);

        return this.modelMapper.map(savedTheme, ThemeServiceModel.class);
    }

    @Override
    public List<ThemeServiceModel> findAll() {

        return this.themeRepository.findAll()
                .stream()
                .map(s -> this.modelMapper.map(s, ThemeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ThemeServiceModel findByID(String id) {

        Theme theme = this.themeRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return this.modelMapper.map(theme, ThemeServiceModel.class);
    }

    @Override
    public void addIndexCarouselImage(ThemeServiceModel themeServiceModel, ImageServiceModel imageServiceModel) {

        themeServiceModel.getIndexCarouselImages().add(imageServiceModel);
        Theme theme = this.modelMapper.map(themeServiceModel, Theme.class);
        List<Image> indexImages = themeServiceModel.getIndexCarouselImages()
                .stream()
                .map(i -> this.imageRepository.findByImageURL(i.getImageURL()).orElse(null))
                .collect(Collectors.toList());
        theme.setIndexCarouselImages(indexImages);
        this.themeRepository.save(theme);
    }

    @Override
    public void addHomeCarouselImage(ThemeServiceModel themeServiceModel, ImageServiceModel imageServiceModel) {

        themeServiceModel.getHomeCarouselImages().add(imageServiceModel);
        Theme theme = this.modelMapper.map(themeServiceModel, Theme.class);
        List<Image> homeImages = themeServiceModel.getHomeCarouselImages()
                .stream()
                .map(i -> this.imageRepository.findByImageURL(i.getImageURL()).orElse(null))
                .collect(Collectors.toList());
        theme.setHomeCarouselImages(homeImages);
        this.themeRepository.save(theme);
    }

    @Override
    public void addAdminPanelImages(ThemeServiceModel themeServiceModel, ImageServiceModel imageServiceModel) {

        themeServiceModel.getAdminPanelImages().add(imageServiceModel);
        Theme theme = this.modelMapper.map(themeServiceModel, Theme.class);
        List<Image> adminPaneImages = themeServiceModel.getAdminPanelImages()
                .stream()
                .map(i -> this.imageRepository.findByImageURL(i.getImageURL()).orElse(null))
                .collect(Collectors.toList());
        theme.setAdminPanelImages(adminPaneImages);

        this.themeRepository.save(theme);
    }

    @Override
    public void deleteThemeImage(String themeID, String imageID) {

        Theme theme = this.themeRepository.findById(themeID).orElse(null);
        Image image = this.imageRepository.findById(imageID).orElse(null);

        List<Image> indexCarouselImages = theme.getIndexCarouselImages()
                .stream()
                .filter(i -> !i.getId().equals(image.getId()))
                .collect(Collectors.toList());

        List<Image> homeCarouselImages = theme.getHomeCarouselImages()
                .stream()
                .filter(i -> !i.getId().equals(image.getId()))
                .collect(Collectors.toList());

        List<Image> adminPanelImages = theme.getAdminPanelImages()
                .stream()
                .filter(i -> !i.getId().equals(image.getId()))
                .collect(Collectors.toList());

        theme.setIndexCarouselImages(indexCarouselImages);
        theme.setHomeCarouselImages(homeCarouselImages);
        theme.setAdminPanelImages(adminPanelImages);

        this.themeRepository.save(theme);
    }

    @Override
    public void deleteTheme(String id) {
        try {
            this.themeRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAdminPanelImages(ThemeServiceModel themeServiceModel) {

        themeServiceModel.setAdminPanelImages(new ArrayList<>());
        Theme theme = this.modelMapper.map(themeServiceModel, Theme.class);
        this.themeRepository.save(theme);
    }

    @Override
    public ThemeServiceModel activateTheme(ThemeServiceModel themeServiceModel) {

        List<Theme> allThemes = this.themeRepository.findAll();
        allThemes.forEach(t -> {
            if(!t.getId().equals(themeServiceModel.getId())) {
                t.setActive(false);
            } else {
                t.setActive(true);
            }
        });

        for (Theme t : allThemes) {
            this.themeRepository.save(t);
        }

       return this.modelMapper
               .map(this.findByID(themeServiceModel.getId()), ThemeServiceModel.class);
    }

    @Override
    public ThemeServiceModel findTheActiveTheme() {

        List<Theme> activeThemes = this.themeRepository.findAll()
                .stream()
                .filter(Theme::getActive)
                .collect(Collectors.toList());

        if(activeThemes.size() == 0) {
            return null;
        }
        return this.modelMapper.map(activeThemes.get(0), ThemeServiceModel.class);
    }
}
