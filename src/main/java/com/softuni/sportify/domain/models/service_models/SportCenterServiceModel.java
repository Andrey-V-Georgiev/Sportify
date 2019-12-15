package com.softuni.sportify.domain.models.service_models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SportCenterServiceModel extends BaseServiceModel {

    private String name;
    private AddressServiceModel address;
    private String description;
    private ImageServiceModel iconImage;
    private List<ImageServiceModel> sportCenterImages;
    private List<SportServiceModel> sports;
    private List<ScheduleServiceModel> calendar;

    public SportCenterServiceModel() {
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

    @NotNull
    public ImageServiceModel getIconImage() {
        return iconImage;
    }

    public void setIconImage(ImageServiceModel iconImage) {
        this.iconImage = iconImage;
    }

    @NotNull
    public List<ImageServiceModel> getSportCenterImages() {
        return sportCenterImages;
    }

    public void setSportCenterImages(List<ImageServiceModel> sportCenterImages) {
        this.sportCenterImages = sportCenterImages;
    }

    @NotNull
    public List<SportServiceModel> getSports() {
        return sports;
    }

    public void setSports(List<SportServiceModel> sports) {
        this.sports = sports;
    }

    @NotNull
    public List<ScheduleServiceModel> getCalendar() {
        return calendar;
    }

    public void setCalendar(List<ScheduleServiceModel> calendar) {
        this.calendar = calendar;
    }
}
