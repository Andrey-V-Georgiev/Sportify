package com.softuni.sportify.domain.models.binding_models;

import com.softuni.sportify.domain.entities.Image;


import java.util.ArrayList;
import java.util.List;

public class SettingCreateBindingModel {

    private String name;
    private List<Image> indexCarouselImages;
    private List<Image> homeCarouselImages;
    private List<Image> adminPanelImages;

    public SettingCreateBindingModel() {
        this.indexCarouselImages = new ArrayList<>();
        this.homeCarouselImages = new ArrayList<>();
        this.adminPanelImages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Image> getIndexCarouselImages() {
        return indexCarouselImages;
    }

    public void setIndexCarouselImages(List<Image> indexCarouselImages) {
        this.indexCarouselImages = indexCarouselImages;
    }

    public List<Image> getHomeCarouselImages() {
        return homeCarouselImages;
    }

    public void setHomeCarouselImages(List<Image> homeCarouselImages) {
        this.homeCarouselImages = homeCarouselImages;
    }

    public List<Image> getAdminPanelImages() {
        return adminPanelImages;
    }

    public void setAdminPanelImages(List<Image> adminPanelImages) {
        this.adminPanelImages = adminPanelImages;
    }
}
