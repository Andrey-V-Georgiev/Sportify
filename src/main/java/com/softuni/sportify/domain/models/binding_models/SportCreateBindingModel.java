package com.softuni.sportify.domain.models.binding_models;

import com.softuni.sportify.domain.entities.SportCenter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class SportCreateBindingModel extends BaseBindingModel {

    private String name;
    private String sportDescription;
    private MultipartFile iconImage;
    private List<MultipartFile> sportImages;

    public SportCreateBindingModel() {
        this.sportImages = new ArrayList<>();
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

    public MultipartFile getIconImage() {
        return iconImage;
    }

    public void setIconImage(MultipartFile iconImage) {
        this.iconImage = iconImage;
    }

    public List<MultipartFile> getSportImages() {
        return sportImages;
    }

    public void setSportImages(List<MultipartFile> sportImages) {
        this.sportImages = sportImages;
    }

}
