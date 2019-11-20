package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageService {

    ImageServiceModel createImage(ImageServiceModel imageServiceModel);

    ImageServiceModel findImageByName(String name);

    List<ImageServiceModel> findAll();
}
