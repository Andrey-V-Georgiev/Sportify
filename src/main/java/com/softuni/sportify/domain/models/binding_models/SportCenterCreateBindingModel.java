package com.softuni.sportify.domain.models.binding_models;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class SportCenterCreateBindingModel extends BaseBindingModel {

    private String name;
    private AddressCreateBindingModel address;
    private String description;
    private MultipartFile iconImage;
    private List<MultipartFile> sportCenterImages;
    private List<SportCreateBindingModel> sports;
    private List<ScheduleCreateBindingModel> calendar;
    public SportCenterCreateBindingModel() {
        this.description = "";
        this.address = new AddressCreateBindingModel();
        this.sportCenterImages = new ArrayList<>();
        this.sports = new ArrayList<>();
        this.calendar = new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddressCreateBindingModel getAddress() {
        return address;
    }

    public void setAddress(AddressCreateBindingModel address) {
        this.address = address;
    }

    public List<MultipartFile> getSportCenterImages() {
        return sportCenterImages;
    }

    public void setSportCenterImages(List<MultipartFile> sportCenterImages) {
        this.sportCenterImages = sportCenterImages;
    }

    public List<SportCreateBindingModel> getSports() {
        return sports;
    }

    public void setSports(List<SportCreateBindingModel> sports) {
        this.sports = sports;
    }

    public List<ScheduleCreateBindingModel> getCalendar() {
        return calendar;
    }

    public void setCalendar(List<ScheduleCreateBindingModel> calendar) {
        this.calendar = calendar;
    }
}
