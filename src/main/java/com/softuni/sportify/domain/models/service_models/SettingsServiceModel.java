package com.softuni.sportify.domain.models.service_models;

import com.softuni.sportify.domain.entities.Image;

import java.util.List;

public class SettingsServiceModel extends BaseServiceModel {

    private List<Image> images;

    public SettingsServiceModel() {
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
