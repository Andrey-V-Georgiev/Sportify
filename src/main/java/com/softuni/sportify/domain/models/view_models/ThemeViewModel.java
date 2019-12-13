package com.softuni.sportify.domain.models.view_models;

import java.util.List;

public class ThemeViewModel extends BaseViewModel {

    private String name;
    private ImageViewModel iconImage;
    private List<ImageViewModel> indexCarouselImages;
    private List<ImageViewModel> homeCarouselImages;
    private List<ImageViewModel> adminPanelImages;
    private Boolean active;

    public ThemeViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageViewModel getIconImage() {
        return iconImage;
    }

    public void setIconImage(ImageViewModel iconImage) {
        this.iconImage = iconImage;
    }

    public List<ImageViewModel> getIndexCarouselImages() {
        return indexCarouselImages;
    }

    public void setIndexCarouselImages(List<ImageViewModel> indexCarouselImages) {
        this.indexCarouselImages = indexCarouselImages;
    }

    public List<ImageViewModel> getHomeCarouselImages() {
        return homeCarouselImages;
    }

    public void setHomeCarouselImages(List<ImageViewModel> homeCarouselImages) {
        this.homeCarouselImages = homeCarouselImages;
    }

    public List<ImageViewModel> getAdminPanelImages() {
        return adminPanelImages;
    }

    public void setAdminPanelImages(List<ImageViewModel> adminPanelImages) {
        this.adminPanelImages = adminPanelImages;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
