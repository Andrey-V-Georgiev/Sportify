package com.softuni.sportify.domain.models.service_models;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.entities.SportCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SportServiceModel extends BaseServiceModel {

    private String name;
    private String sportDescription;
    private ImageServiceModel descriptionImage;
    private ImageServiceModel iconImage;
    private List<ImageServiceModel> sportImages;

    public SportServiceModel() {

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

}
