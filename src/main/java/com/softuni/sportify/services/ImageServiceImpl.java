package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.repositories.ImageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public ImageServiceModel findImageByName(String name) {
        Image image = this.imageRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Image with this name doesn't exists!"));
        return this.modelMapper.map(image, ImageServiceModel.class);
    }

    @Override
    public List<ImageServiceModel> findAll() {
        List<Image> allImages = this.imageRepository.findAll();
        return allImages
                .stream()
                .map(i->this.modelMapper.map(i, ImageServiceModel.class))
                .collect(Collectors.toList());
    }
}
