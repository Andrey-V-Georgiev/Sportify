package com.softuni.sportify.domain.models.binding_models;

import org.springframework.web.multipart.MultipartFile;

public class ImageCreateBindingModel extends BaseBindingModel {

    private String name;
    private MultipartFile image;
    private String publicID;

    public ImageCreateBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getPublicID() {
        return publicID;
    }

    public void setPublicID(String publicID) {
        this.publicID = publicID;
    }

}
