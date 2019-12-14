package com.softuni.sportify.domain.models.binding_models;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ImageCreateBindingModel extends BaseBindingModel {

    private String name;
    private MultipartFile image;
    private String publicID;

    public ImageCreateBindingModel() {
    }

    @NotNull
    @Size(min = 2, max = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @NotNull
    public String getPublicID() {
        return publicID;
    }

    public void setPublicID(String publicID) {
        this.publicID = publicID;
    }

}
