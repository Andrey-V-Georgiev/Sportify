package com.softuni.sportify.domain.models.view_models;

import java.util.List;

public class SportCenterViewModel extends BaseViewModel {

    private String name;
    private AddressViewModel address;
    private String description;
    private ImageViewModel iconImage;
    private List<ImageViewModel> sportCenterImages;
    private List<SportViewModel> sports;
    private List<ScheduleViewModel> calendar;

    public SportCenterViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressViewModel getAddress() {
        return address;
    }

    public void setAddress(AddressViewModel address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageViewModel getIconImage() {
        return iconImage;
    }

    public void setIconImage(ImageViewModel iconImage) {
        this.iconImage = iconImage;
    }

    public List<ImageViewModel> getSportCenterImages() {
        return sportCenterImages;
    }

    public void setSportCenterImages(List<ImageViewModel> sportCenterImages) {
        this.sportCenterImages = sportCenterImages;
    }

    public List<SportViewModel> getSports() {
        return sports;
    }

    public void setSports(List<SportViewModel> sports) {
        this.sports = sports;
    }

    public List<ScheduleViewModel> getCalendar() {
        return calendar;
    }

    public void setCalendar(List<ScheduleViewModel> calendar) {
        this.calendar = calendar;
    }
}
