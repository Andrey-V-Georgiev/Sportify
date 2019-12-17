package com.softuni.sportify.factory;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;

public abstract class ImageFactory {

    public static ImageServiceModel createValidImageServiceModel() {

        ImageServiceModel imageServiceModel = new ImageServiceModel();
        imageServiceModel.setName("name");
        imageServiceModel.setPublicID("publicId");
        imageServiceModel.setImageURL("imageUrl");
        return imageServiceModel;
    }

    public static Image createValidImage() {

        Image image = new Image();
        image.setName("name");
        image.setPublicID("publicId");
        image.setImageURL("imageUrl");
        return image;
    }
}
