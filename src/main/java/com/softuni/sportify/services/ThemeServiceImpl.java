package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.entities.Theme;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.ThemeServiceModel;
import com.softuni.sportify.exceptions.*;
import com.softuni.sportify.repositories.ImageRepository;
import com.softuni.sportify.repositories.ThemeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.ExceptionConstants.*;

@Service
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;
    private final Validator validator;

    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository,
                            ModelMapper modelMapper,
                            ImageRepository imageRepository,
                            Validator validator) {
        this.themeRepository = themeRepository;
        this.modelMapper = modelMapper;
        this.imageRepository = imageRepository;
        this.validator = validator;
    }

    @Override
    public ThemeServiceModel createNewTheme(ThemeServiceModel themeServiceModel,
                                            ImageServiceModel imageServiceModel) throws CreateException {

        themeServiceModel.setActive(false);
        themeServiceModel.setIconImage(imageServiceModel);
        Set<ConstraintViolation<ThemeServiceModel>> validateThemeServiceModel = validator.validate(themeServiceModel);
        Set<ConstraintViolation<ImageServiceModel>> validateImageServiceModel = validator.validate(imageServiceModel);

        if(!validateThemeServiceModel.isEmpty() || !validateImageServiceModel.isEmpty()) {
            throw new CreateException(THEME_CREATE_EXCEPTION_MSG);
        }
        Theme theme = this.modelMapper.map(themeServiceModel, Theme.class);
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
    public ThemeServiceModel findByID(String id) throws ReadException {

        Theme theme = this.themeRepository.findById(id)
                .orElseThrow(()-> new ReadException(THEME_READ_EXCEPTION_MSG));
        return this.modelMapper.map(theme, ThemeServiceModel.class);
    }

    @Override
    public void addIndexCarouselImage(ThemeServiceModel themeServiceModel,
                                      ImageServiceModel imageServiceModel) throws UpdateException {

        if(!validator.validate(themeServiceModel).isEmpty() ||
                !validator.validate(imageServiceModel).isEmpty()) {
            throw new UpdateException(THEME_UPDATE_EXCEPTION_MSG);
        }
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
    public void addHomeCarouselImage(ThemeServiceModel themeServiceModel,
                                     ImageServiceModel imageServiceModel) throws UpdateException {

        if(!validator.validate(themeServiceModel).isEmpty() ||
                !validator.validate(imageServiceModel).isEmpty()) {
            throw new UpdateException(THEME_UPDATE_EXCEPTION_MSG);
        }
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
    public void addAdminPanelImages(ThemeServiceModel themeServiceModel,
                                    ImageServiceModel imageServiceModel) throws UpdateException {

        if(!validator.validate(themeServiceModel).isEmpty() ||
                !validator.validate(imageServiceModel).isEmpty()) {
            throw new UpdateException(THEME_UPDATE_EXCEPTION_MSG);
        }
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
    public void deleteThemeImage(String themeID,
                                 String imageID) throws UpdateException {

        Theme theme = this.themeRepository.findById(themeID)
                .orElseThrow(()-> new UpdateException(THEME_UPDATE_EXCEPTION_MSG));
        Image image = this.imageRepository.findById(imageID)
                .orElseThrow(()-> new UpdateException(THEME_UPDATE_EXCEPTION_MSG));

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
    public void deleteTheme(String id) throws DeleteException {

        try {
            this.themeRepository.deleteById(id);
        } catch (Exception e) {
            throw new DeleteException(THEME_DELETE_EXCEPTION_MSG);
        }
    }

    @Override
    public void deleteAdminPanelImages(ThemeServiceModel themeServiceModel) throws UpdateException {

        if(!validator.validate(themeServiceModel).isEmpty()) {
            throw new UpdateException(THEME_UPDATE_EXCEPTION_MSG);
        }
        themeServiceModel.setAdminPanelImages(new ArrayList<>());
        Theme theme = this.modelMapper.map(themeServiceModel, Theme.class);
        this.themeRepository.save(theme);
    }

    @Override
    public ThemeServiceModel activateTheme(ThemeServiceModel themeServiceModel) throws UpdateException, ReadException {

        if(!validator.validate(themeServiceModel).isEmpty()) {
            throw new UpdateException(THEME_UPDATE_EXCEPTION_MSG);
        }
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
