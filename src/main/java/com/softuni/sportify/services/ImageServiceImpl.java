package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.exceptions.*;
import com.softuni.sportify.repositories.ImageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validator;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.ExceptionConstants.IMAGE_CREATE_EXCEPTION_MSG;
import static com.softuni.sportify.constants.ThemesControllerConstants.*;
import static com.softuni.sportify.constants.ExceptionConstants.*;

@Service
public class ImageServiceImpl implements ImageService {

    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;
    private final CloudinaryService cloudinaryService;
    private final Validator validator;

    @Autowired
    public ImageServiceImpl(ModelMapper modelMapper,
                            ImageRepository imageRepository,
                            CloudinaryService cloudinaryService,
                            Validator validator) {
        this.modelMapper = modelMapper;
        this.imageRepository = imageRepository;
        this.cloudinaryService = cloudinaryService;
        this.validator = validator;
    }

    @Override
    public ImageServiceModel createImage(ImageServiceModel imageServiceModel,
                                         String name) throws CreateException {

        imageServiceModel.setPublicID(this.obtainPublicID(imageServiceModel.getImageURL()));
        imageServiceModel.setName(name);
        if(!validator.validate(imageServiceModel).isEmpty()) {
            throw new CreateException(IMAGE_CREATE_EXCEPTION_MSG);
        }
        Image image = this.modelMapper.map(imageServiceModel, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);

        return this.modelMapper.map(savedImage, ImageServiceModel.class);
    }

    @Override
    public ImageServiceModel createImageMultipartFile(MultipartFile multipartFile,
                                                      String name) throws IOException, CreateException {

        ImageServiceModel imageServiceModel = new ImageServiceModel();
        try {
            imageServiceModel.setImageURL(this.cloudinaryService.uploadImage(multipartFile));
        } catch (Exception e) {
            throw new CreateException(IMAGE_CREATE_EXCEPTION_MSG);
        }
        imageServiceModel.setPublicID(this.obtainPublicID(imageServiceModel.getImageURL()));
        return this.createImage(imageServiceModel, name);
    }

    @Override
    public ImageServiceModel editImage(ImageServiceModel imageServiceModel) throws UpdateException {

        if(!validator.validate(imageServiceModel).isEmpty()) {
            throw new UpdateException(IMAGE_UPDATE_EXCEPTION_MSG);
        }
        Image image = this.modelMapper.map(imageServiceModel, Image.class);
        Image editedImage = this.imageRepository.saveAndFlush(image);

        return this.modelMapper.map(editedImage, ImageServiceModel.class);
    }

    @Override
    public void deleteImage(String id) throws Exception{
        Image image = this.imageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Image with this id is not found!"));
        ImageServiceModel imageServiceModel = this.modelMapper.map(image, ImageServiceModel.class);
        if(!validator.validate(imageServiceModel).isEmpty()) {
            throw new DeleteException(IMAGE_DELETE_EXCEPTION_MSG);
        }
        this.cloudinaryService.deleteImage(imageServiceModel.getPublicID());

        this.imageRepository.delete(image);
    }

    @Override
    public ImageServiceModel findImageByID(String id) throws ReadException {

        Image image = this.imageRepository.findById(id)
                .orElseThrow(()-> new ReadException(IMAGE_READ_EXCEPTION_MSG));
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

        Pattern pattern = Pattern.compile(OBTAIN_PUBLIC_ID_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(imageURL);
        String publicID = "";
        while (matcher.find()) {
            publicID = imageURL.substring(matcher.start(), matcher.end());
        }
        return publicID;
    }
}
