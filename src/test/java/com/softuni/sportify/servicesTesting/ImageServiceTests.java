package com.softuni.sportify.servicesTesting;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import com.softuni.sportify.repositories.*;
import com.softuni.sportify.services.CloudinaryService;
import com.softuni.sportify.services.ImageService;
import com.softuni.sportify.services.ImageServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.Validator;

import static com.softuni.sportify.factory.ImageFactory.createValidImage;
import static com.softuni.sportify.factory.ImageFactory.createValidImageServiceModel;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ImageServiceTests {

    @Autowired
    private ImageRepository imageRepository;
    @MockBean
    private CloudinaryService cloudinaryService;

    private ModelMapper modelMapper = new ModelMapper();
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private ImageService imageService;
    private ImageServiceModel validImageServiceModel;
    private ImageServiceModel validImageServiceModel2;
    private final String STAMAT = "Stamat";

    @Before
    public void init() {

        this.imageService = new ImageServiceImpl(
                this.modelMapper, this.imageRepository, this.cloudinaryService, this.validator);

        Image validImage = createValidImage();
        validImageServiceModel = this.modelMapper.map(validImage, ImageServiceModel.class);

        Image validImage2 = createValidImage();
        validImageServiceModel2 = this.modelMapper.map(validImage2, ImageServiceModel.class);
    }

    /* ImageServiceModel createImage(ImageServiceModel imageServiceModel, String name) */
    @Test
    public void createImage_whenModelIsValid_imageCreated() throws CreateException {

        ImageServiceModel actual = imageService.createImage(validImageServiceModel, STAMAT);
        ImageServiceModel expected = this.modelMapper
                .map(this.imageRepository.findAll().get(0), ImageServiceModel.class);

        assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = Exception.class)
    public void createEvent_whenModelIsEmpty_throwsException() throws CreateException {

        ImageServiceModel imageServiceModel = createValidImageServiceModel();
        this.imageService.createImage(imageServiceModel, "");
    }

    /* ImageServiceModel editImage(ImageServiceModel imageServiceModel) */
    @Test
    public void editImage_whenModelIsValid_correctEditResult() throws UpdateException {

        Image image = this.modelMapper.map(validImageServiceModel, Image.class);
        Image expectedImage = this.imageRepository.saveAndFlush(image);
        String expectedPublicID = expectedImage.getPublicID();

        validImageServiceModel2.setId(expectedImage.getId());
        validImageServiceModel2.setPublicID("editedPublicID");
        ImageServiceModel actualImage = this.imageService.editImage(validImageServiceModel2);
        String actualPublicID = actualImage.getPublicID();

        assertEquals(expectedImage.getId(), actualImage.getId());
        assertEquals(expectedPublicID, "defaultPublicId");
        assertEquals(actualPublicID, "editedPublicID");
    }

    @Test(expected = Exception.class)
    public void editImage_whenModelIsNotValid_throwException() throws UpdateException {

        validImageServiceModel.setName("");
        this.imageService.editImage(validImageServiceModel);
    }

    /* void deleteImage(String id)  */
    @Test
    public void deleteImage_whenModelIsValid_successfulDelete() throws Exception {

        Image image = this.modelMapper.map(validImageServiceModel, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        int beforeDbSize = this.imageRepository.findAll().size();

        this.imageService.deleteImage(savedImage.getId());
        int expectedDbSize = 0;
        int actualDbSize = this.imageRepository.findAll().size();

        assertEquals(beforeDbSize, 1);
        assertEquals(expectedDbSize, actualDbSize);
    }

    @Test(expected = Exception.class)
    public void deleteImage_whenModelIsNotValid_throwException() throws Exception {

        Image image = this.modelMapper.map(validImageServiceModel, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        savedImage.setName("");
        this.imageService.deleteImage(savedImage.getId());
    }

    /* ImageServiceModel findImageByID(String id)   */
    @Test
    public void findById_whenIdIsValid_correctEvent() throws ReadException {

        Image image = this.modelMapper.map(validImageServiceModel, Image.class);
        Image expected = this.imageRepository.saveAndFlush(image);
        ImageServiceModel actual = this.imageService.findImageByID(expected.getId());

        assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = Exception.class)
    public void findById_whenIdIsNotValid_throwException() throws ReadException {

        ImageServiceModel actual = this.imageService.findImageByID("wringId");
    }

    /*  List<ImageServiceModel> findAll() */
    @Test
    public void findAll_whenDbHasTwoImages_correctResult() throws ReadException {

        Image image1 = this.modelMapper.map(validImageServiceModel, Image.class);
        this.imageRepository.saveAndFlush(image1);
        Image image2 = this.modelMapper.map(validImageServiceModel2, Image.class);
        this.imageRepository.saveAndFlush(image2);

        int expectedSize = 2;
        int actualSize = this.imageService.findAll().size();

        assertEquals(expectedSize, actualSize);
    }

}
