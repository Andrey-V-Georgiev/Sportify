package com.softuni.sportify.domain.models.service_models;

import java.util.ArrayList;
import java.util.List;

public class SettingServiceModel extends BaseServiceModel {

    private String name;
    private List<ImageServiceModel> indexCarouselImages;
    private List<ImageServiceModel> homeCarouselImages;
    private List<ImageServiceModel> adminPanelImages;

    public SettingServiceModel() {
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

    public List<ImageServiceModel> getIndexCarouselImages() {
        return indexCarouselImages;
    }

    public void setIndexCarouselImages(List<ImageServiceModel> indexCarouselImages) {
        this.indexCarouselImages = indexCarouselImages;
    }

    public List<ImageServiceModel> getHomeCarouselImages() {
        return homeCarouselImages;
    }

    public void setHomeCarouselImages(List<ImageServiceModel> homeCarouselImages) {
        this.homeCarouselImages = homeCarouselImages;
    }

    public List<ImageServiceModel> getAdminPanelImages() {
        return adminPanelImages;
    }

    public void setAdminPanelImages(List<ImageServiceModel> adminPanelImages) {
        this.adminPanelImages = adminPanelImages;
    }
}
