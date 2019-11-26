package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.entities.Setting;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SettingServiceModel;
import com.softuni.sportify.repositories.ImageRepository;
import com.softuni.sportify.repositories.SettingsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettingsServiceImpl implements SettingsService {

    private final SettingsRepository settingsRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;

    @Autowired
    public SettingsServiceImpl(SettingsRepository settingsRepository,
                               ModelMapper modelMapper,
                               ImageRepository imageRepository) {
        this.settingsRepository = settingsRepository;
        this.modelMapper = modelMapper;
        this.imageRepository = imageRepository;
    }

    @Override
    public SettingServiceModel createNewSetting(SettingServiceModel settingServiceModel) {

        Setting setting = this.modelMapper.map(settingServiceModel, Setting.class);
        Setting savedSetting = this.settingsRepository.saveAndFlush(setting);

        return this.modelMapper.map(savedSetting, SettingServiceModel.class);
    }

    @Override
    public List<SettingServiceModel> findAll() {
        return this.settingsRepository.findAll()
                .stream()
                .map(s -> this.modelMapper.map(s, SettingServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public SettingServiceModel findByID(String id) {

        Setting setting = this.settingsRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return this.modelMapper.map(setting, SettingServiceModel.class);
    }

    @Override
    public void addIndexCarouselImage(SettingServiceModel settingServiceModel, ImageServiceModel imageServiceModel) {
        settingServiceModel.getIndexCarouselImages().add(imageServiceModel);
        Setting setting = this.modelMapper.map(settingServiceModel, Setting.class);
        List<Image> indexImages = settingServiceModel.getIndexCarouselImages()
                .stream()
                .map(i -> this.imageRepository.findByImageURL(i.getImageURL()).orElse(null))
                .collect(Collectors.toList());
        setting.setIndexCarouselImages(indexImages);
        this.settingsRepository.save(setting);
    }

    @Override
    public void addHomeCarouselImage(SettingServiceModel settingServiceModel, ImageServiceModel imageServiceModel) {
        settingServiceModel.getHomeCarouselImages().add(imageServiceModel);
        Setting setting = this.modelMapper.map(settingServiceModel, Setting.class);
        List<Image> homeImages = settingServiceModel.getHomeCarouselImages()
                .stream()
                .map(i -> this.imageRepository.findByImageURL(i.getImageURL()).orElse(null))
                .collect(Collectors.toList());
        setting.setHomeCarouselImages(homeImages);
        this.settingsRepository.save(setting);
    }
}
