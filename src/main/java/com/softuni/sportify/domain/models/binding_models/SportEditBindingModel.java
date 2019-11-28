package com.softuni.sportify.domain.models.binding_models;

import com.softuni.sportify.domain.entities.SportCenter;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class SportEditBindingModel {

    private String name;
    private String sportDescription;
    private ImageServiceModel descriptionImage;
    private ImageServiceModel iconImage;
    private List<ImageServiceModel> sportImages;
    private List<SportCenter> sportCenters;

    public SportEditBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSportDescription() {
        return sportDescription;
    }

    public void setSportDescription(String sportDescription) {
        this.sportDescription = sportDescription;
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

    public List<ImageServiceModel> getSportImages() {
        return sportImages;
    }

    public void setSportImages(List<ImageServiceModel> sportImages) {
        this.sportImages = sportImages;
    }

    public List<SportCenter> getSportCenters() {
        return sportCenters;
    }

    public void setSportCenters(List<SportCenter> sportCenters) {
        this.sportCenters = sportCenters;
    }
}
