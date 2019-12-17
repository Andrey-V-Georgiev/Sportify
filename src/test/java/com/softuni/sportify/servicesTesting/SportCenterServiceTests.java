package com.softuni.sportify.servicesTesting;

import com.softuni.sportify.domain.entities.Address;
import com.softuni.sportify.domain.entities.Image;
import com.softuni.sportify.domain.entities.Sport;
import com.softuni.sportify.domain.entities.SportCenter;
import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.DeleteException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import com.softuni.sportify.repositories.*;
import com.softuni.sportify.services.SportCenterService;
import com.softuni.sportify.services.SportCenterServiceImpl;
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

import java.util.ArrayList;

import static com.softuni.sportify.factory.AddressFactory.createValidAddress;
import static com.softuni.sportify.factory.ImageFactory.createValidImage;
import static com.softuni.sportify.factory.SportCenterFactory.createValidSportCenter;
import static com.softuni.sportify.factory.SportFactory.createValidSport;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SportCenterServiceTests {

    @Autowired
    private SportCenterRepository sportCenterRepository;
    @Autowired
    private SportRepository sportRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private AddressRepository addressRepository;
    @MockBean
    private SportCenterService sportCenterService;

    private ModelMapper modelMapper = new ModelMapper();
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private SportCenterServiceModel validSportCenterServiceModel1;
    private SportCenterServiceModel validSportCenterServiceModel2;
    private ImageServiceModel validImageServiceModel3;
    private SportServiceModel validSportServiceModel4;

    @Before
    public void init() {
        this.sportCenterService = new SportCenterServiceImpl(
                this.modelMapper, this.sportCenterRepository,
                this.sportRepository, this.imageRepository, this.validator);

        /* CREATE VALID SPORT CENTER SERVICE MODEL */
        SportCenter validSportCenter1 = createValidSportCenter();
        Address validAddress1 = createValidAddress();
        Address savedAddress1 = this.addressRepository.saveAndFlush(validAddress1);
        Image validImage1 = createValidImage();
        Image savedImage1 = this.imageRepository.saveAndFlush(validImage1);
        validSportCenter1.setAddress(savedAddress1);
        validSportCenter1.setIconImage(savedImage1);
        validSportCenterServiceModel1 = this.modelMapper.map(validSportCenter1, SportCenterServiceModel.class);

        /* CREATE VALID SPORT CENTER SERVICE MODEL 2 */
        SportCenter validSportCenter2 = createValidSportCenter();
        Address validAddress2 = createValidAddress();
        Address savedAddress2 = this.addressRepository.saveAndFlush(validAddress2);
        Image validImage2 = createValidImage();
        Image savedImage2 = this.imageRepository.saveAndFlush(validImage2);
        validSportCenter2.setAddress(savedAddress2);
        validSportCenter2.setIconImage(savedImage2);
        validSportCenterServiceModel2 = this.modelMapper.map(validSportCenter2, SportCenterServiceModel.class);

        /* CREATE VALID IMAGE SERVICE MODEL 3 */
        Image validImage3 = createValidImage();
        validImageServiceModel3 = this.modelMapper.map(validImage3, ImageServiceModel.class);

        /* CREATE VALID SPORT SERVICE MODEL 4 */
        Image validImage4 = createValidImage();
        Image savedImageSport4 = this.imageRepository.saveAndFlush(validImage4);
        Sport validSport4 = createValidSport();
        validSport4.setIconImage(savedImageSport4);
        validSportServiceModel4 = this.modelMapper.map(validSport4, SportServiceModel.class);
    }

    /* SportCenterServiceModel createSportCenter(SportCenterServiceModel sportCenterServiceModel)  */
    @Test
    public void createSportCenter_whenModelIsValid_successfulCreation() throws CreateException {

        SportCenterServiceModel expected = this.sportCenterService.createSportCenter(validSportCenterServiceModel1);
        SportCenter actual = this.sportCenterRepository.findAll().get(0);
        assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = Exception.class)
    public void createSportCenter_whenModelIsNotValid_throwsException() throws CreateException {

        validSportCenterServiceModel1.setCalendar(null);
        SportCenterServiceModel expected = this.sportCenterService.createSportCenter(validSportCenterServiceModel1);
    }


    /*  SportCenterServiceModel findByID(String id) */
    @Test
    public void findByID_whenModelIsValid_correctResult() throws CreateException, ReadException {

        SportCenterServiceModel expected = this.sportCenterService.createSportCenter(validSportCenterServiceModel1);
        SportCenterServiceModel actual = this.sportCenterService.findByID(expected.getId());
        assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = Exception.class)
    public void findByID_whenModelIsNotValid_throwsException() throws ReadException {

        SportCenterServiceModel actual = this.sportCenterService.findByID("wrongId");
    }


    /*  List<SportCenterServiceModel> findAllSportCenters() */
    @Test
    public void findAllSportCenters_whenDbSizeIs2_correctResult() throws CreateException {

        SportCenterServiceModel scsm1 = this.sportCenterService.createSportCenter(validSportCenterServiceModel1);
        SportCenterServiceModel scsm2 = this.sportCenterService.createSportCenter(validSportCenterServiceModel2);

        int expected = 2;
        int actual = this.sportCenterService.findAllSportCenters().size();
        assertEquals(expected, actual);
    }

    /* SportCenterServiceModel editSportCenterAddress(SportCenterServiceModel sportCenterServiceModel)  */

    @Test(expected = Exception.class)
    public void editSportCenterAddress_whenModelIsNotValid_throwsException() throws UpdateException {

        SportCenter sportCenter = this.modelMapper.map(validSportCenterServiceModel1, SportCenter.class);
        SportCenterServiceModel sportCenterServiceModel = this.modelMapper
                .map(this.sportCenterRepository.saveAndFlush(sportCenter), SportCenterServiceModel.class);
        sportCenterServiceModel.setCalendar(null);
        this.sportCenterService.editSportCenterAddress(sportCenterServiceModel);
    }


    /*  SportCenterServiceModel addSportCenterImage(SportCenterServiceModel sportCenterServiceModel,
                                                       ImageServiceModel imageServiceModel) */
    @Test
    public void addSportCenterImage_whenModelIsValid_successfullyAdded() throws CreateException, UpdateException {

        Image image = this.modelMapper.map(validImageServiceModel3, Image.class);
        ImageServiceModel imageServiceModel = this.modelMapper.map(image, ImageServiceModel.class);

        SportCenterServiceModel savedSportCenterServiceModel = this.sportCenterService
                .createSportCenter(validSportCenterServiceModel1);
        int initialSize = savedSportCenterServiceModel.getSportCenterImages().size();

        this.sportCenterService.addSportCenterImage(savedSportCenterServiceModel, imageServiceModel);
        SportCenter updatedSportCenter = this.sportCenterRepository
                .findById(savedSportCenterServiceModel.getId()).orElse(null);
        int updatedSize = updatedSportCenter.getSportCenterImages().size();

        assertNotEquals(initialSize, updatedSize);
    }

    @Test(expected = Exception.class)
    public void addSportCenterImage_whenModelIsNull_throwsException() throws CreateException, UpdateException {

        SportCenterServiceModel savedSportCenterServiceModel = this.sportCenterService
                .createSportCenter(validSportCenterServiceModel1);

        this.sportCenterService.addSportCenterImage(savedSportCenterServiceModel, null);
    }

    /*  SportCenterServiceModel updateSportCenter(SportCenterServiceModel sportCenterServiceModel) */

    @Test(expected = Exception.class)
    public void updateSportCenter_whenModelIsNotValid_throwsException() throws CreateException, UpdateException {

        SportCenterServiceModel savedSportCenterServiceModel = this.sportCenterService
                .createSportCenter(validSportCenterServiceModel1);
        savedSportCenterServiceModel.setIconImage(null);
        this.sportCenterService.updateSportCenter(savedSportCenterServiceModel);
    }


    /* SportCenterServiceModel updateSportCenterSports(SportCenterServiceModel sportCenterServiceModel,
                                                           List<String> sportCenterIDs)  */
    @Test(expected = Exception.class)
    public void updateSportCenterSports_whenInputIsNotValid_throwsException() throws CreateException, UpdateException {

        SportCenterServiceModel savedSportCenterServiceModel = this.sportCenterService
                .createSportCenter(validSportCenterServiceModel1);
        savedSportCenterServiceModel.setCalendar(null);
        this.sportCenterService.updateSportCenterSports(savedSportCenterServiceModel, new ArrayList<>());
    }


    /*  void removeCurrentSport(SportServiceModel sportServiceModel) */
    @Test
    public void removeCurrentSport_whenModelIsValid_successfullyRemoved() throws CreateException, UpdateException {

        Sport savedSport = this.sportRepository
                .saveAndFlush(this.modelMapper.map(validSportServiceModel4, Sport.class));
        SportServiceModel sportServiceModel = this.modelMapper.map(savedSport, SportServiceModel.class);

        SportCenterServiceModel savedSportCenterServiceModel = this.sportCenterService
                .createSportCenter(validSportCenterServiceModel1);
        savedSportCenterServiceModel.getSports().add(sportServiceModel);

        int initialSize = savedSportCenterServiceModel.getSports().size();
        this.sportCenterService.removeCurrentSport(sportServiceModel);

        SportCenter updatedSportCenter = this.sportCenterRepository
                .findById(savedSportCenterServiceModel.getId()).orElse(null);
        int updatedSize = updatedSportCenter.getSports().size();

        assertNotEquals(initialSize, updatedSize);
    }

    @Test(expected = Exception.class)
    public void removeCurrentSport_whenModelIsNotValid_throwsException() throws CreateException, UpdateException {

        Sport savedSport = this.sportRepository
                .saveAndFlush(this.modelMapper.map(validSportServiceModel4, Sport.class));
        SportServiceModel sportServiceModel = this.modelMapper.map(savedSport, SportServiceModel.class);

        SportCenterServiceModel savedSportCenterServiceModel = this.sportCenterService
                .createSportCenter(validSportCenterServiceModel1);
        savedSportCenterServiceModel.getSports().add(sportServiceModel);

        sportServiceModel.setName("");
        this.sportCenterService.removeCurrentSport(sportServiceModel);
    }

    /*  void deleteSportCenterImage(String sportCenterID, String imageID) */
    @Test
    public void deleteSportCenterImage_whenInputIsValid_successfullyDeleted() throws UpdateException, CreateException, DeleteException {

        Image image = this.modelMapper.map(validImageServiceModel3, Image.class);
        ImageServiceModel imageServiceModel = this.modelMapper.map(image, ImageServiceModel.class);

        SportCenterServiceModel savedSportCenterServiceModel = this.sportCenterService
                .createSportCenter(validSportCenterServiceModel1);

        SportCenterServiceModel updatedSportCenterServiceModel = this.sportCenterService
                .addSportCenterImage(savedSportCenterServiceModel, imageServiceModel);
        int initialSize = updatedSportCenterServiceModel.getSportCenterImages().size();

        this.sportCenterService.deleteSportCenterImage(updatedSportCenterServiceModel.getId(),
                        updatedSportCenterServiceModel.getSportCenterImages().get(0).getId());

        SportCenter sportCenter = this.sportCenterRepository.findAll().get(0);
        int updatedSize = sportCenter.getSportCenterImages().size();

        assertNotEquals(initialSize, updatedSize);
    }

    @Test(expected = Exception.class)
    public void deleteSportCenterImage_whenImageIdIsNull_throwsException() throws CreateException, UpdateException, DeleteException {

        Image image = this.modelMapper.map(validImageServiceModel3, Image.class);
        ImageServiceModel imageServiceModel = this.modelMapper.map(image, ImageServiceModel.class);

        SportCenterServiceModel savedSportCenterServiceModel = this.sportCenterService
                .createSportCenter(validSportCenterServiceModel1);

        SportCenterServiceModel updatedSportCenterServiceModel = this.sportCenterService
                .addSportCenterImage(savedSportCenterServiceModel, imageServiceModel);
        int initialSize = updatedSportCenterServiceModel.getSportCenterImages().size();

        this.sportCenterService.deleteSportCenterImage(updatedSportCenterServiceModel.getId(), null);
    }

    /*  void deleteSportCenter(SportCenterServiceModel sportCenterServiceModel) */
    @Test
    public void deleteSportCenter_whenModelIsValid_successfully() throws CreateException, UpdateException, DeleteException {

        SportCenterServiceModel savedSportCenterServiceModel = this.sportCenterService
                .createSportCenter(validSportCenterServiceModel1);
        int initialSize = this.sportCenterRepository.findAll().size();
        this.sportCenterService.deleteSportCenter(savedSportCenterServiceModel);
        int updatedSize = this.sportCenterRepository.findAll().size();

        assertNotEquals(initialSize, updatedSize);
    }

    @Test(expected = Exception.class)
    public void deleteSportCenter_whenModelIsNotValid_throwsException()
            throws CreateException, UpdateException, DeleteException {

        SportCenterServiceModel savedSportCenterServiceModel = this.sportCenterService
                .createSportCenter(validSportCenterServiceModel1);
        savedSportCenterServiceModel.setName("");
        this.sportCenterService.deleteSportCenter(savedSportCenterServiceModel);
    }

}
