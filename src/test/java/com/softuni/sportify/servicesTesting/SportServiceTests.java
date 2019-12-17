package com.softuni.sportify.servicesTesting;

import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.entities.Sport;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.DeleteException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import com.softuni.sportify.repositories.ImageRepository;
import com.softuni.sportify.repositories.SportRepository;
import com.softuni.sportify.services.EventService;
import com.softuni.sportify.services.SportCenterService;
import com.softuni.sportify.services.SportService;
import com.softuni.sportify.services.SportServiceImpl;
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

import java.util.List;

import static com.softuni.sportify.factory.ImageFactory.createValidImage;
import static com.softuni.sportify.factory.SportFactory.createValidSport;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SportServiceTests {

    @Autowired
    private SportRepository sportRepository;
    @Autowired
    private ImageRepository imageRepository;
    @MockBean
    private SportService sportService;
    @MockBean
    private SportCenterService sportCenterService;
    @MockBean
    private EventService eventService;

    private ModelMapper modelMapper = new ModelMapper();
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private SportServiceModel validSportServiceModel1;
    private ImageServiceModel validImageServiceModel2;
    private SportServiceModel validSportServiceModel3;
    private ImageServiceModel validImageServiceModel4;

    @Before
    public void init() {
        this.sportService = new SportServiceImpl(
                this.modelMapper, this.sportRepository, this.imageRepository,
                this.sportCenterService, this.eventService, this.validator);

        /* CREATE SPORT SERVICE MODEL 1 */
        Image validImage1 = createValidImage();
        Image savedImageSport1 = this.imageRepository.saveAndFlush(validImage1);
        Sport validSport1 = createValidSport();
        validSport1.setIconImage(savedImageSport1);
        validSportServiceModel1 = this.modelMapper.map(validSport1, SportServiceModel.class);

        /* CREATE IMAGE SERVICE MODEL 2 */
        Image validImage2 = createValidImage();
        validImageServiceModel2 = this.modelMapper.map(validImage2, ImageServiceModel.class);

        /* CREATE SPORT SERVICE MODEL 3 */
        Image validImage3 = createValidImage();
        Image savedImageSport3 = this.imageRepository.saveAndFlush(validImage3);
        Sport validSport3 = createValidSport();
        validSport1.setIconImage(savedImageSport3);
        validSportServiceModel3 = this.modelMapper.map(validSport3, SportServiceModel.class);

        /* CREATE IMAGE SERVICE MODEL 4 */
        Image validImage4 = createValidImage();
        validImageServiceModel4 = this.modelMapper.map(validImage4, ImageServiceModel.class);
    }

    /* SportServiceModel createSport(SportServiceModel sportServiceModel,
                                         ImageServiceModel iconImageServiceModel)  */

    @Test
    public void createSport_whenModelsAreValid_successfullyCreated() throws CreateException {


        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);
        SportServiceModel expected = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel);

        Sport actual = this.sportRepository.findAll().get(0);
        assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = Exception.class)
    public void createSport_whenModelsAreNotValid_throwsException() throws CreateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);
        validSportServiceModel1.setName("");
        SportServiceModel expected = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel);
    }


    /*  SportServiceModel findByID(String id) */
    @Test
    public void findByID_whenInputIsValid_correctResult() throws CreateException, ReadException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);
        SportServiceModel expected = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel);
        SportServiceModel actual = this.sportService.findByID(expected.getId());
        assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = Exception.class)
    public void findByID_whenInputIsNotValid_throwsException() throws CreateException, ReadException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);
        SportServiceModel expected = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel);
        SportServiceModel actual = this.sportService.findByID("wrongId");
    }


    /* SportServiceModel findByName(String name)  */
    @Test
    public void findByName_whenInputIsValid_correctResult() throws CreateException, ReadException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);
        SportServiceModel expected = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel);

        SportServiceModel actual = this.sportService.findByName(expected.getName());
        assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = Exception.class)
    public void findByName_whenInputIsNotValid_throwsException() throws CreateException, ReadException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);
        SportServiceModel expected = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel);

        SportServiceModel actual = this.sportService.findByName("wrongName");
    }


    /* List<SportServiceModel> findAllSports() */
    @Test
    public void findAllSports_whenDbSizeIs2_correctResult() throws CreateException, ReadException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);
        SportServiceModel sportServiceModel = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel);

        int expectedSize = 1;
        int actualSize = this.sportService.findAllSports().size();
        assertEquals(expectedSize, actualSize);
    }

    /*  List<String> findAllSportsNames() */
    @Test
    public void findAllSportsNames_whenDbSizeIs2_correctResult() throws CreateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);
        validSportServiceModel1.setName("expectedName");
        SportServiceModel sportServiceModel = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel);

        String expectedName = "expectedName";
        String actualName = this.sportService.findAllSportsNames().get(0);
        assertEquals(expectedName, actualName);
    }

    /* List<String> findAllSportsNamesStartsWith(String id)  */
    @Test
    public void findAllSportsNamesStartsWith_whenInputValid_correctResult() throws CreateException, ReadException {

        /* create first sport */
        Image image1 = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage1 = this.imageRepository.saveAndFlush(image1);
        ImageServiceModel imageServiceModel1 = this.modelMapper.map(savedImage1, ImageServiceModel.class);
        SportServiceModel sportServiceModel1 = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel1);

        /* create second sport */
        Image image2 = this.modelMapper.map(validImageServiceModel4, Image.class);
        Image savedImage2 = this.imageRepository.saveAndFlush(image2);
        ImageServiceModel imageServiceModel2 = this.modelMapper.map(savedImage2, ImageServiceModel.class);
        SportServiceModel sportServiceModel2 = this.sportService
                .createSport(validSportServiceModel3,imageServiceModel2);

        String expectedName = sportServiceModel2.getName();
        List<String> allSportsNamesStartsWith = this.sportService
                .findAllSportsNamesStartsWith(sportServiceModel2.getId());
        String actualName = allSportsNamesStartsWith.get(0);

        assertEquals(expectedName, actualName);
    }

    @Test(expected = Exception.class)
    public void findAllSportsNamesStartsWith_whenInputIsNotValid_throwsException() throws CreateException, ReadException {

        /* create first sport */
        Image image1 = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage1 = this.imageRepository.saveAndFlush(image1);
        ImageServiceModel imageServiceModel1 = this.modelMapper.map(savedImage1, ImageServiceModel.class);
        SportServiceModel sportServiceModel1 = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel1);

        /* create second sport */
        Image image2 = this.modelMapper.map(validImageServiceModel4, Image.class);
        Image savedImage2 = this.imageRepository.saveAndFlush(image2);
        ImageServiceModel imageServiceModel2 = this.modelMapper.map(savedImage2, ImageServiceModel.class);
        SportServiceModel sportServiceModel2 = this.sportService
                .createSport(validSportServiceModel3,imageServiceModel2);

        List<String> allSportsNamesStartsWith = this.sportService
                .findAllSportsNamesStartsWith("wrongId");
    }


    /* SportServiceModel addSportImage(SportServiceModel sportServiceModel,
                                           ImageServiceModel imageServiceModel)  */
    @Test
    public void addSportImage_whenInputIsValid_successfullyAdded() throws CreateException, UpdateException {

        Image image1 = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image saveImage1 = this.imageRepository.saveAndFlush(image1);
        ImageServiceModel imageServiceModel1 = this.modelMapper.map(saveImage1, ImageServiceModel.class);

        SportServiceModel sportServiceModel = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel1);

        Image image2 = this.modelMapper.map(validImageServiceModel4, Image.class);
        ImageServiceModel imageServiceModel2 = this.modelMapper.map(image2, ImageServiceModel.class);

        int initialSize = sportServiceModel.getSportImages().size();

        this.sportService.addSportImage(sportServiceModel, imageServiceModel2);
        Sport sport = this.sportRepository.findById(sportServiceModel.getId()).orElse(null);
        int updatedSize = sport.getSportImages().size();

        assertNotEquals(updatedSize, initialSize);
    }

    @Test(expected = Exception.class)
    public void addSportImage_whenInputIsNotValid_throwsException() throws CreateException, UpdateException {

        Image image1 = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image saveImage1 = this.imageRepository.saveAndFlush(image1);
        ImageServiceModel imageServiceModel1 = this.modelMapper.map(saveImage1, ImageServiceModel.class);

        SportServiceModel sportServiceModel = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel1);
        sportServiceModel.setName("");

        Image image2 = this.modelMapper.map(validImageServiceModel4, Image.class);
        ImageServiceModel imageServiceModel2 = this.modelMapper.map(image2, ImageServiceModel.class);

        this.sportService.addSportImage(sportServiceModel, imageServiceModel2);
    }


    /*  SportServiceModel updateSportDescription(SportServiceModel sportServiceModel)   */

    @Test(expected = Exception.class)
    public void updateSportDescription_whenModelIsNull_throwsException() throws UpdateException {

        this.sportService.updateSportDescription(null);
    }


    /* void deleteSportImage(String sportID, String imageID)  */
    @Test
    public void deleteSportImage_whenInputIsValid_successfullyDeleted() throws CreateException, UpdateException {

        Image image1 = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image saveImage1 = this.imageRepository.saveAndFlush(image1);
        ImageServiceModel imageServiceModel1 = this.modelMapper.map(saveImage1, ImageServiceModel.class);

        SportServiceModel sportServiceModel = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel1);

        Image image2 = this.modelMapper.map(validImageServiceModel4, Image.class);
        ImageServiceModel imageServiceModel2 = this.modelMapper.map(image2, ImageServiceModel.class);

        this.sportService.addSportImage(sportServiceModel, imageServiceModel2);
        Sport sport = this.sportRepository.findById(sportServiceModel.getId()).orElse(null);
        int initialSize = sport.getSportImages().size();


        String imageId = sport.getSportImages().get(0).getId();
        this.sportService.deleteSportImage(sportServiceModel.getId(), imageId);
        Sport sport2 = this.sportRepository.findById(sportServiceModel.getId()).orElse(null);
        int actualSize = sport.getSportImages().size();

        assertNotEquals(initialSize, actualSize);
    }

    @Test(expected = Exception.class)
    public void deleteSportImage_whenInputIsNotValid_throwsException() throws CreateException, UpdateException {

        Image image1 = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image saveImage1 = this.imageRepository.saveAndFlush(image1);
        ImageServiceModel imageServiceModel1 = this.modelMapper.map(saveImage1, ImageServiceModel.class);

        SportServiceModel sportServiceModel = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel1);

        Image image2 = this.modelMapper.map(validImageServiceModel4, Image.class);
        ImageServiceModel imageServiceModel2 = this.modelMapper.map(image2, ImageServiceModel.class);

        this.sportService.addSportImage(sportServiceModel, imageServiceModel2);
        Sport sport = this.sportRepository.findById(sportServiceModel.getId()).orElse(null);
        int initialSize = sport.getSportImages().size();

        this.sportService.deleteSportImage(sportServiceModel.getId(), "wrongId");
    }


    /* void deleteSport(SportServiceModel sportServiceModel)  */
    @Test
    public void deleteSport_whenModelIsValid_successfullyDeleted()
            throws CreateException, UpdateException, DeleteException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);
        SportServiceModel sportServiceModel = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel);
        int initialSize =  this.sportRepository.findAll().size();
        this.sportService.deleteSport(sportServiceModel);
        int updatedSize =  this.sportRepository.findAll().size();
        assertNotEquals(initialSize, updatedSize);
    }

    @Test(expected = Exception.class)
    public void deleteSport_whenModelIsNotValid_throwsException() throws UpdateException, DeleteException, CreateException {

        Image image = this.modelMapper.map(validImageServiceModel2, Image.class);
        Image savedImage = this.imageRepository.saveAndFlush(image);
        ImageServiceModel imageServiceModel = this.modelMapper.map(savedImage, ImageServiceModel.class);
        SportServiceModel sportServiceModel = this.sportService
                .createSport(validSportServiceModel1,imageServiceModel);
        sportServiceModel.setName("");
        this.sportService.deleteSport(sportServiceModel);
    }

}
