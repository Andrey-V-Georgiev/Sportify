package com.softuni.sportify.domain.models.service_models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ThemeServiceModel extends BaseServiceModel {

    private String name;
    private ImageServiceModel iconImage;
    private List<ImageServiceModel> indexCarouselImages;
    private List<ImageServiceModel> homeCarouselImages;
    private List<ImageServiceModel> adminPanelImages;
    private Boolean active;

    public ThemeServiceModel() {

    }

    @NotNull
    @Size(min = 2, max = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public ImageServiceModel getIconImage() {
        return iconImage;
    }

    public void setIconImage(ImageServiceModel iconImage) {
        this.iconImage = iconImage;
    }

    @NotNull
    public List<ImageServiceModel> getIndexCarouselImages() {
        return indexCarouselImages;
    }

    public void setIndexCarouselImages(List<ImageServiceModel> indexCarouselImages) {
        this.indexCarouselImages = indexCarouselImages;
    }

    @NotNull
    public List<ImageServiceModel> getHomeCarouselImages() {
        return homeCarouselImages;
    }

    public void setHomeCarouselImages(List<ImageServiceModel> homeCarouselImages) {
        this.homeCarouselImages = homeCarouselImages;
    }

    @NotNull
    public List<ImageServiceModel> getAdminPanelImages() {
        return adminPanelImages;
    }

    public void setAdminPanelImages(List<ImageServiceModel> adminPanelImages) {
        this.adminPanelImages = adminPanelImages;
    }

    @NotNull
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
