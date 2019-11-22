package com.softuni.sportify.domain.models.binding_models;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.entities.SportCenter;

import java.util.Set;

public class SportAddNewBindingModel extends BaseBindingModel {

    private String name;
    private String description;
    private Set<Image> images;
    private Set<SportCenter> sportCenters;

    public SportAddNewBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<SportCenter> getSportCenters() {
        return sportCenters;
    }

    public void setSportCenters(Set<SportCenter> sportCenters) {
        this.sportCenters = sportCenters;
    }
}
