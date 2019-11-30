package com.softuni.sportify.domain.models.binding_models;

import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;

import java.util.List;

public class SportCenterEditBindingModel extends BaseBindingModel {

    private String name;
    private ImageServiceModel iconImage;

    private String description;
    private AddressEditBindingModel address;
    private List<ImageServiceModel> sportCenterImages;
    private List<SportServiceModel> sports;
    private List<EventServiceModel> events;

    public SportCenterEditBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageServiceModel getIconImage() {
        return iconImage;
    }

    public void setIconImage(ImageServiceModel iconImage) {
        this.iconImage = iconImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddressEditBindingModel getAddress() {
        return address;
    }

    public void setAddress(AddressEditBindingModel address) {
        this.address = address;
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
