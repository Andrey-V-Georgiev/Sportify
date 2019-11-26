package com.softuni.sportify.domain.models.service_models;

public class ImageServiceModel extends BaseServiceModel {

    private String name;
    private String imageURL;
    private String publicID;

    public ImageServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPublicID() {
        return publicID;
    }

    public void setPublicID(String publicID) {
        this.publicID = publicID;
    }

}
