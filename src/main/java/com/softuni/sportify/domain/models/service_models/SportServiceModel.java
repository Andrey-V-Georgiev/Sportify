package com.softuni.sportify.domain.models.service_models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SportServiceModel extends BaseServiceModel {

    private String name;
    private String sportDescription;
    private ImageServiceModel iconImage;
    private List<ImageServiceModel> sportImages;

    public SportServiceModel() {

    }

    @NotNull
    @Size(min = 2, max = 30)
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

    @NotNull
    public ImageServiceModel getIconImage() {
        return iconImage;
    }

    public void setIconImage(ImageServiceModel iconImage) {
        this.iconImage = iconImage;
    }

    @NotNull
    public List<ImageServiceModel> getSportImages() {
        return sportImages;
    }

    public void setSportImages(List<ImageServiceModel> sportImages) {
        this.sportImages = sportImages;
    }

}
