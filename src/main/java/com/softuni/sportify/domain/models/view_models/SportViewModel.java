package com.softuni.sportify.domain.models.view_models;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;

import java.util.List;

public class SportViewModel extends BaseViewModel {

    private String name;
    private String sportDescription;
    private ImageViewModel iconImage;
    private List<ImageViewModel> sportImages;

    public SportViewModel() {
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

    public ImageViewModel getIconImage() {
        return iconImage;
    }

    public void setIconImage(ImageViewModel iconImage) {
        this.iconImage = iconImage;
    }

    public List<ImageViewModel> getSportImages() {
        return sportImages;
    }

    public void setSportImages(List<ImageViewModel> sportImages) {
        this.sportImages = sportImages;
    }
}
