package com.softuni.sportify.domain.models.binding_models;

import com.softuni.sportify.domain.entities.SportCenter;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SportEditBindingModel {

    private String name;
    private String sportDescription;
    private ImageServiceModel iconImage;
    private List<ImageServiceModel> sportImages;

    public SportEditBindingModel() {
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
