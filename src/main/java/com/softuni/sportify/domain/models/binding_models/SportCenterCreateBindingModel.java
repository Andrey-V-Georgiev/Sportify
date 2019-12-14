package com.softuni.sportify.domain.models.binding_models;

import com.softuni.sportify.validation.ImageMultipartFileConstraints;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private String country;
    private String city;
    private String street;
    private String details;

    public SportCenterCreateBindingModel() {
        this.description = "";
        this.address = new AddressCreateBindingModel();
        this.sportCenterImages = new ArrayList<>();
        this.sports = new ArrayList<>();
        this.calendar = new ArrayList<>();
    }

    @NotNull
    @Size(min = 2, max = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ImageMultipartFileConstraints
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

    @NotNull
    @Size(min = 3, max = 40)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @NotNull
    @Size(min = 2, max = 40)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @NotNull
    @Size(min = 2, max = 40)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @NotNull
    @Size(min = 8, max = 200)
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
