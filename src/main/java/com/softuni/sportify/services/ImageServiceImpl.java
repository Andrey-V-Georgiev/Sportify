package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.repositories.ImageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.ImagesControllerConstants.*;

@Service
public class ImageServiceImpl implements ImageService {

    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public ImageServiceImpl(ModelMapper modelMapper,
                            ImageRepository imageRepository, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.imageRepository = imageRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public String obtainPublicID(String imageURL) {

        Pattern pattern = Pattern.compile(OBTAIN_PUBLIC_ID_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(imageURL);
        String publicID = "";
        while (matcher.find()) {
            publicID = imageURL.substring(matcher.start(), matcher.end());
        }
        return publicID;
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
    public void deleteImage(String id) throws Exception {
        Image image = this.imageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Image with this id is not found!"));
        ImageServiceModel imageServiceModel = this.modelMapper.map(image, ImageServiceModel.class);
        this.cloudinaryService.deleteImage(imageServiceModel.getPublicID());

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

}
