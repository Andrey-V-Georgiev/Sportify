package com.softuni.sportify.domain.models.binding_models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ImageEditBindingModel extends BaseBindingModel {

    private String name;
    private String imageURL;
    private String publicID;

    public ImageEditBindingModel() {
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
    @Size(min = 10)
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @NotNull
    public String getPublicID() {
        return publicID;
    }

    public void setPublicID(String publicID) {
        this.publicID = publicID;
    }

}
