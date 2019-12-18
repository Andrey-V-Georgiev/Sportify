package com.softuni.sportify.servicesTesting;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.entities.Theme;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.ThemeServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.DeleteException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import com.softuni.sportify.repositories.ImageRepository;
import com.softuni.sportify.repositories.ThemeRepository;
import com.softuni.sportify.services.ThemeService;
import com.softuni.sportify.services.ThemeServiceImpl;
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

import java.util.stream.Collectors;

import static com.softuni.sportify.factory.ImageFactory.createValidImage;
import static com.softuni.sportify.factory.ThemeFactory.createValidTheme;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ThemeServiceTests {

    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private ImageRepository imageRepository;
    @MockBean
    private ThemeService themeService;
    private ModelMapper modelMapper = new ModelMapper();
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private ThemeServiceModel validThemeServiceModel1;
    private ImageServiceModel validImageServiceModel2;
    private ImageServiceModel validImageServiceModel3;

    @Before
    public void init() {
        this.themeService = new ThemeServiceImpl(
                this.themeRepository, this.modelMapper,
                this.imageRepository, this.validator);

        /* CREATE THEME SERVICE MODEL 1 */
        Theme validTheme1 = createValidTheme();
        validThemeServiceModel1 = this.modelMapper.map(validTheme1, ThemeServiceModel.class);

        /* CREATE IOMAGE SERVICE MODEL 2 */
        Image validImage2 = createValidImage();
        validImageServiceModel2 = this.modelMapper.map(validImage2, ImageServiceModel.class);

        /* CREATE IOMAGE SERVICE MODEL 3 */
        Image validImage3 = createValidImage();
        validImageServiceModel3 = this.modelMapper.map(validImage3, ImageServiceModel.class);
    }

    /* ThemeServiceModel createNewTheme(ThemeServiceModel themeServiceModel,
                                            ImageServiceModel imageServiceModel) */
    @Test
    public void createNewTheme_whenModelAreValid_successfulCreation() throws CreateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper.map(validThemeServiceModel1, ThemeServiceModel.class);
        this.themeService.createNewTheme(themeServiceModel, imageServiceModel);

        int expectedSize = 1;
        int actualSize = this.themeRepository.findAll().size();
        assertEquals(expectedSize, actualSize);
    }

    @Test(expected = Exception.class)
    public void createNewTheme_whenModelsAreNotValid_throwsException() throws CreateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper.map(validThemeServiceModel1, ThemeServiceModel.class);
        themeServiceModel.setName("");
        this.themeService.createNewTheme(themeServiceModel, imageServiceModel);
    }


    /* List<ThemeServiceModel> findAll() */
    @Test
    public void findAll_whenDbSize1_correctResult() throws CreateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper.map(validThemeServiceModel1, ThemeServiceModel.class);
        this.themeService.createNewTheme(themeServiceModel, imageServiceModel);

        int expectedSize = 1;
        int actualSize = this.themeRepository.findAll().size();
        assertEquals(expectedSize, actualSize);
    }


    /* ThemeServiceModel findByID(String id) */
    @Test
    public void findByID_whenInputIsValid_correctResult() throws CreateException, ReadException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(validThemeServiceModel1, ThemeServiceModel.class);
        ThemeServiceModel savedThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);

        String expectedId = savedThemeServiceModel.getId();
        String actualId = this.themeService.findByID(expectedId).getId();
        assertEquals(expectedId, actualId);
    }

    @Test(expected = Exception.class)
    public void findByID_whenInputIsNotValid_throwsException() throws ReadException {

        String actualId = this.themeService.findByID("wrongId").getId();
    }


    /* ThemeServiceModel findTheActiveTheme() */
    @Test
    public void findTheActiveTheme_whenActiveThemeIsSet_correctResult() throws CreateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(validThemeServiceModel1, ThemeServiceModel.class);

        ThemeServiceModel savedThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);
        Theme theme = this.themeRepository.findById(savedThemeServiceModel.getId()).orElse(null);
        theme.setActive(true);
        this.themeRepository.save(theme);

        Theme expected = this.themeRepository.findAll()
                    .stream()
                    .filter(t -> t.getActive())
                    .collect(Collectors.toList())
                    .get(0);

        ThemeServiceModel actual = this.themeService.findTheActiveTheme();
        assertEquals(expected.getId(), actual.getId());
    }

    /*  ThemeServiceModel activateTheme(ThemeServiceModel themeServiceModel)  */
    @Test
    public void activateTheme_whenModelIsValid_successfully() throws ReadException, UpdateException, CreateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(validThemeServiceModel1, ThemeServiceModel.class);

        ThemeServiceModel savedThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);
        this.themeService.activateTheme(savedThemeServiceModel);

        Theme expected = this.themeRepository.findAll()
                .stream()
                .filter(t -> t.getActive())
                .collect(Collectors.toList())
                .get(0);

        ThemeServiceModel actual = this.themeService.findTheActiveTheme();
        assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = Exception.class)
    public void activateTheme_whenModelIsNotValid_throwsException()
            throws CreateException, ReadException, UpdateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(validThemeServiceModel1, ThemeServiceModel.class);

        ThemeServiceModel savedThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);
        savedThemeServiceModel.setAdminPanelImages(null);
        this.themeService.activateTheme(savedThemeServiceModel);
    }

    /* addIndexCarouselImage(ThemeServiceModel themeServiceModel,
                                      ImageServiceModel imageServiceModel) */
    @Test
    public void addIndexCarouselImage_whenModelsAreValid_successfullyAdded() throws CreateException, UpdateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(validThemeServiceModel1, ThemeServiceModel.class);

        ThemeServiceModel savedThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);

        int initialSize = savedThemeServiceModel.getIndexCarouselImages().size();
        this.themeService.addIndexCarouselImage(savedThemeServiceModel, validImageServiceModel3);
        Theme theme = this.themeRepository.findById(savedThemeServiceModel.getId()).orElse(null);
        int updatedSize = theme.getIndexCarouselImages().size();

        assertNotEquals(initialSize, updatedSize);
    }

    @Test(expected = Exception.class)
    public void addIndexCarouselImage_whenModelsAreNotValid_throwsException() throws CreateException, UpdateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(validThemeServiceModel1, ThemeServiceModel.class);

        ThemeServiceModel savedThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);

        int initialSize = savedThemeServiceModel.getIndexCarouselImages().size();
        validImageServiceModel3.setName("");
        this.themeService.addIndexCarouselImage(savedThemeServiceModel, validImageServiceModel3);
    }


    /* addHomeCarouselImage(ThemeServiceModel themeServiceModel,
                                     ImageServiceModel imageServiceModel) */
    @Test
    public void addHomeCarouselImage_whenModelsAreValid_successfullyAdded() throws CreateException, UpdateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(validThemeServiceModel1, ThemeServiceModel.class);

        ThemeServiceModel savedThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);

        int initialSize = savedThemeServiceModel.getHomeCarouselImages().size();
        this.themeService.addHomeCarouselImage(savedThemeServiceModel, validImageServiceModel3);
        Theme theme = this.themeRepository.findById(savedThemeServiceModel.getId()).orElse(null);
        int updatedSize = theme.getHomeCarouselImages().size();

        assertNotEquals(initialSize, updatedSize);
    }

    @Test(expected = Exception.class)
    public void addHomeCarouselImage_whenModelsAreNotValid_throwsException() throws CreateException, UpdateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(validThemeServiceModel1, ThemeServiceModel.class);

        ThemeServiceModel savedThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);

        int initialSize = savedThemeServiceModel.getHomeCarouselImages().size();
        validImageServiceModel3.setName("");
        this.themeService.addHomeCarouselImage(savedThemeServiceModel, validImageServiceModel3);
    }


    /* addAdminPanelImages(ThemeServiceModel themeServiceModel,
                                    ImageServiceModel imageServiceModel) */
    @Test
    public void addAdminPanelImages_whenModelsAreValid_successfullyAdded() throws CreateException, UpdateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(validThemeServiceModel1, ThemeServiceModel.class);

        ThemeServiceModel savedThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);

        int initialSize = savedThemeServiceModel.getAdminPanelImages().size();
        this.themeService.addAdminPanelImages(savedThemeServiceModel, validImageServiceModel3);
        Theme theme = this.themeRepository.findById(savedThemeServiceModel.getId()).orElse(null);
        int updatedSize = theme.getAdminPanelImages().size();

        assertNotEquals(initialSize, updatedSize);
    }

    @Test(expected = Exception.class)
    public void addAdminPanelImages_whenModelsAreNotValid_throwsException() throws CreateException, UpdateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(validThemeServiceModel1, ThemeServiceModel.class);

        ThemeServiceModel savedThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);

        int initialSize = savedThemeServiceModel.getAdminPanelImages().size();
        validImageServiceModel3.setName("");
        this.themeService.addAdminPanelImages(savedThemeServiceModel, validImageServiceModel3);
    }

    /* void deleteThemeImage(String themeID, String imageID)  */
    @Test
    public void deleteThemeImage_whenInputIsValid_successfullyDeleted() throws CreateException, UpdateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(validThemeServiceModel1, ThemeServiceModel.class);

        ThemeServiceModel savedThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);

        this.themeService.addAdminPanelImages(savedThemeServiceModel, validImageServiceModel3);

        Theme themeBeforeDelete = this.themeRepository.findById(savedThemeServiceModel.getId()).orElse(null);
        int initialSize = themeBeforeDelete.getAdminPanelImages().size();
        String imageId = themeBeforeDelete.getAdminPanelImages().get(0).getId();
        this.themeService.deleteThemeImage(savedThemeServiceModel.getId(), imageId);
        Theme themeAfterDelete = this.themeRepository.findById(savedThemeServiceModel.getId()).orElse(null);
        int updatedSize = themeAfterDelete.getAdminPanelImages().size();

        assertNotEquals(initialSize, updatedSize);
    }

    @Test(expected = Exception.class)
    public void deleteThemeImage_whenImageIdIsNull_throwsException() throws CreateException, UpdateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(validThemeServiceModel1, ThemeServiceModel.class);

        ThemeServiceModel savedThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);

        this.themeService.addAdminPanelImages(savedThemeServiceModel, validImageServiceModel3);

        Theme themeBeforeDelete = this.themeRepository.findById(savedThemeServiceModel.getId()).orElse(null);
        int initialSize = themeBeforeDelete.getAdminPanelImages().size();
        String imageId = themeBeforeDelete.getAdminPanelImages().get(0).getId();
        this.themeService.deleteThemeImage(savedThemeServiceModel.getId(), null);
    }

    /* void deleteTheme(String id) */
    @Test
    public void deleteTheme_whenInputIsValid_successfullyDeleted() throws CreateException, DeleteException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(validThemeServiceModel1, ThemeServiceModel.class);

        ThemeServiceModel savedThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);
        int initialSize = this.themeRepository.findAll().size();
        this.themeService.deleteTheme(savedThemeServiceModel.getId());
        int updatedSize = this.themeRepository.findAll().size();

        assertNotEquals(initialSize, updatedSize);
    }

    @Test(expected = Exception.class)
    public void deleteTheme_whenInputIsNotValid_throwsException() throws DeleteException {

        this.themeService.deleteTheme("wrongId");
    }


    /* void deleteAdminPanelImages(ThemeServiceModel themeServiceModel) */
    @Test
    public void deleteAdminPanelImages_whenModelIsValid_successfullyDeleted() throws CreateException, UpdateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(validThemeServiceModel1, ThemeServiceModel.class);

        ThemeServiceModel savedThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);

        this.themeService.addAdminPanelImages(savedThemeServiceModel, validImageServiceModel3);
        Theme themeBeforeDelete = this.themeRepository.findById(savedThemeServiceModel.getId()).orElse(null);
        int initialSize = themeBeforeDelete.getAdminPanelImages().size();

        this.themeService.deleteAdminPanelImages(savedThemeServiceModel);
        Theme themeAfterDelete = this.themeRepository.findById(savedThemeServiceModel.getId()).orElse(null);
        int updatedSize = themeBeforeDelete.getAdminPanelImages().size();

        assertNotEquals(initialSize, updatedSize);
    }

    @Test(expected = Exception.class)
    public void deleteAdminPanelImages_whenModelIsNotValid_throwsException() throws CreateException, UpdateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);

        ThemeServiceModel themeServiceModel = this.modelMapper
                .map(validThemeServiceModel1, ThemeServiceModel.class);

        ThemeServiceModel savedThemeServiceModel = this.themeService
                .createNewTheme(themeServiceModel, imageServiceModel);

        this.themeService.addAdminPanelImages(savedThemeServiceModel, validImageServiceModel3);
        Theme themeBeforeDelete = this.themeRepository.findById(savedThemeServiceModel.getId()).orElse(null);
        int initialSize = themeBeforeDelete.getAdminPanelImages().size();

        savedThemeServiceModel.setIconImage(null);
        this.themeService.deleteAdminPanelImages(savedThemeServiceModel);
    }

}
