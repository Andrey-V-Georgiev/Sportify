package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ImageService {

    ImageServiceModel createImage(ImageServiceModel imageServiceModel);

    ImageServiceModel createImageMultipartFile(MultipartFile multipartFile, String name) throws IOException;

    ImageServiceModel editImage(ImageServiceModel imageServiceModel);

    void deleteImage(String id) throws Exception;

    ImageServiceModel findImageByName(String name);

    ImageServiceModel findImageByID(String id);

    ImageServiceModel findByImageURL(String imageURL);

    List<ImageServiceModel> findAll();

    String obtainPublicID(String imageURL);
}
