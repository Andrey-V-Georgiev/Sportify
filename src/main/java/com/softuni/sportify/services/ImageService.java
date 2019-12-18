package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ImageService {

    ImageServiceModel createImage(ImageServiceModel imageServiceModel, String name) throws CreateException;

    ImageServiceModel createImageMultipartFile(MultipartFile multipartFile, String name) throws IOException, CreateException;

    ImageServiceModel editImage(ImageServiceModel imageServiceModel) throws UpdateException;

    void deleteImage(String id) throws Exception;

    ImageServiceModel findImageByID(String id) throws ReadException;

    List<ImageServiceModel> findAll();

    String obtainPublicID(String imageURL);
}
