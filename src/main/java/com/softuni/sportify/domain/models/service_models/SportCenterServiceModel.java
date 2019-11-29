package com.softuni.sportify.domain.models.service_models;

import java.util.List;

public class SportCenterServiceModel extends BaseServiceModel {

    private String name;
    private AddressServiceModel address;
    private String description;
    private ImageServiceModel descriptionImage;
    private ImageServiceModel iconImage;
    private List<ImageServiceModel> sportCenterImages;
    private List<SportServiceModel> sports;
    private List<EventServiceModel> events;

    public SportCenterServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressServiceModel getAddress() {
        return address;
    }

    public void setAddress(AddressServiceModel address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageServiceModel getDescriptionImage() {
        return descriptionImage;
    }

    public void setDescriptionImage(ImageServiceModel descriptionImage) {
        this.descriptionImage = descriptionImage;
    }

    public ImageServiceModel getIconImage() {
        return iconImage;
    }

    public void setIconImage(ImageServiceModel iconImage) {
        this.iconImage = iconImage;
    }

    public List<ImageServiceModel> getSportCenterImages() {
        return sportCenterImages;
    }

    public void setSportCenterImages(List<ImageServiceModel> sportCenterImages) {
        this.sportCenterImages = sportCenterImages;
    }

    public List<SportServiceModel> getSports() {
        return sports;
    }

    public void setSports(List<SportServiceModel> sports) {
        this.sports = sports;
    }

    public List<EventServiceModel> getEvents() {
        return events;
    }

    public void setEvents(List<EventServiceModel> events) {
        this.events = events;
    }
}
