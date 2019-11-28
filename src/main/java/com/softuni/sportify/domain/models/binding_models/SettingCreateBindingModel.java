package com.softuni.sportify.domain.models.binding_models;

import com.softuni.sportify.domain.entities.Image;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;

public class SettingCreateBindingModel {

    private String name;
    private MultipartFile iconImage;
    private List<MultipartFile> indexCarouselImages;
    private List<MultipartFile> homeCarouselImages;
    private List<MultipartFile> adminPanelImages;

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

    public MultipartFile getIconImage() {
        return iconImage;
    }

    public void setIconImage(MultipartFile iconImage) {
        this.iconImage = iconImage;
    }

    public List<MultipartFile> getIndexCarouselImages() {
        return indexCarouselImages;
    }

    public void setIndexCarouselImages(List<MultipartFile> indexCarouselImages) {
        this.indexCarouselImages = indexCarouselImages;
    }

    public List<MultipartFile> getHomeCarouselImages() {
        return homeCarouselImages;
    }

    public void setHomeCarouselImages(List<MultipartFile> homeCarouselImages) {
        this.homeCarouselImages = homeCarouselImages;
    }

    public List<MultipartFile> getAdminPanelImages() {
        return adminPanelImages;
    }

    public void setAdminPanelImages(List<MultipartFile> adminPanelImages) {
        this.adminPanelImages = adminPanelImages;
    }
}
