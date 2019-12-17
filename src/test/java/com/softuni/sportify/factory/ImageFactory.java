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
        image.setPublicID("defaultPublicId");
        image.setImageURL("http://res.cloudinary.com/ordnata/image/upload/v1576508775/aaaaaaaaaaaaaaaaaaaa.jpg");
        return image;
    }
}
