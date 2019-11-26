package com.softuni.sportify.domain.models.binding_models;

import com.softuni.sportify.domain.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class SettingCreateBindingModel {

    private String name;
    private List<Image> indexCarousel;
    private List<Image> homeCarousel;
    private List<Image> settingsImages;

    public SettingCreateBindingModel() {
        this.indexCarousel = new ArrayList<>();
        this.homeCarousel = new ArrayList<>();
        this.settingsImages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Image> getIndexCarousel() {
        return indexCarousel;
    }

    public void setIndexCarousel(List<Image> indexCarousel) {
        this.indexCarousel = indexCarousel;
    }

    public List<Image> getHomeCarousel() {
        return homeCarousel;
    }

    public void setHomeCarousel(List<Image> homeCarousel) {
        this.homeCarousel = homeCarousel;
    }

    public List<Image> getSettingsImages() {
        return settingsImages;
    }

    public void setSettingsImages(List<Image> settingsImages) {
        this.settingsImages = settingsImages;
    }
}
