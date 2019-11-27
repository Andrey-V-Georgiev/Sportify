package com.softuni.sportify.domain.models.binding_models;

public class ImageEditBindingModel extends BaseBindingModel {

    private String name;
    private String imageURL;
    private String publicID;
    private String ownerObjectID;

    public ImageEditBindingModel() {
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

    public String getOwnerObjectID() {
        return ownerObjectID;
    }

    public void setOwnerObjectID(String ownerObjectID) {
        this.ownerObjectID = ownerObjectID;
    }
}
