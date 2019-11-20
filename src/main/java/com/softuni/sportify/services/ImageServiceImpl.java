package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.repositories.ImageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ModelMapper modelMapper,
                            ImageRepository imageRepository) {
        this.modelMapper = modelMapper;
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageServiceModel createImage(ImageServiceModel imageServiceModel) {
        Image checkImage = this.imageRepository.findByName(imageServiceModel.getName()).orElse(null);

        if (checkImage != null) {
            throw new IllegalArgumentException("Image with this name already exists!");
        }

        Image image = this.modelMapper.map(imageServiceModel, Image.class);
        this.imageRepository.saveAndFlush(image);
        return this.modelMapper.map(image, ImageServiceModel.class);
    }

    @Override
    public ImageServiceModel editImage(ImageServiceModel imageServiceModel) {

        Image image = this.imageRepository.findById(imageServiceModel.getId())
                .orElseThrow(() -> new IllegalArgumentException("Image with this id is not found!"));
        this.modelMapper.map(imageServiceModel, image);
        Image editedImage = this.imageRepository.saveAndFlush(image);
        return this.modelMapper.map(editedImage, ImageServiceModel.class);
    }

    @Override
    public void deleteImage(String id) {
        Image image = this.imageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Image with this id is not found!"));
        this.imageRepository.delete(image);
    }

    @Override
    public ImageServiceModel findImageByName(String name) {
        Image image = this.imageRepository.findByName(name).orElse(null);
        if (image == null) {
            return new ImageServiceModel();
        }
        return this.modelMapper.map(image, ImageServiceModel.class);
    }

    @Override
    public ImageServiceModel findImageByID(String id) {
        Image image = this.imageRepository.findById(id).orElse(null);
        if (image == null) {
            return new ImageServiceModel();
        }
        return this.modelMapper.map(image, ImageServiceModel.class);
    }

    @Override
    public List<ImageServiceModel> findAll() {
        List<Image> allImages = this.imageRepository.findAll();
        return allImages
                .stream()
                .map(i -> this.modelMapper.map(i, ImageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public String obtainPublicID(String imageURL) {
        String patternStr = "(?<=upload/).*(?=\\.jpg)";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(imageURL);
        boolean matches = Pattern.matches(imageURL, patternStr);
        String publicID = "";
        while (matcher.find()) {
            publicID = imageURL.substring(matcher.start(), matcher.end());
        }

        return publicID;
    }
}
