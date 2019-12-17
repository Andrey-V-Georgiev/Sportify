package com.softuni.sportify.servicesTesting;

import com.softuni.sportify.domain.entities.*;
import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.DeleteException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import com.softuni.sportify.repositories.*;
import com.softuni.sportify.services.EventService;
import com.softuni.sportify.services.ScheduleService;
import com.softuni.sportify.services.ScheduleServiceImpl;
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

import static com.softuni.sportify.factory.AddressFactory.*;
import static com.softuni.sportify.factory.EventFactory.*;
import static com.softuni.sportify.factory.ImageFactory.*;
import static com.softuni.sportify.factory.ScheduleFactory.*;
import static com.softuni.sportify.factory.SportCenterFactory.*;
import static com.softuni.sportify.factory.SportFactory.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ScheduleServiceTests {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private SportCenterRepository sportCenterRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private SportRepository sportRepository;
    @MockBean
    private EventService eventService;
    @MockBean
    private ScheduleService scheduleService;

    private ModelMapper modelMapper = new ModelMapper();
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private SportCenterServiceModel validSportCenterServiceModel1;
    private EventServiceModel validEventServiceModel2;
    private EventServiceModel validEventServiceModel3;
    private ScheduleServiceModel validScheduleServiceModel4;

    @Before
    public void init() {
        this.scheduleService = new ScheduleServiceImpl(
                this.scheduleRepository, this.modelMapper,
                this.sportCenterRepository, this.eventService, this.validator);

        /* CREATE VALID SPORT CENTER SERVICE MODEL */
        SportCenter validSportCenter1 = createValidSportCenter();
        Address validAddress1 = createValidAddress();
        Address savedAddress1 = this.addressRepository.saveAndFlush(validAddress1);
        Image validImage1 = createValidImage();
        Image savedImage1 = this.imageRepository.saveAndFlush(validImage1);
        validSportCenter1.setAddress(savedAddress1);
        validSportCenter1.setIconImage(savedImage1);
        validSportCenterServiceModel1 = this.modelMapper.map(validSportCenter1, SportCenterServiceModel.class);

        /* CREATE EVENT SERVICE MODEL 2 */
        Image validImage2 = createValidImage();
        Image savedImageSport2 = this.imageRepository.saveAndFlush(validImage2);
        Sport validSport2 = createValidSport();
        validSport2.setIconImage(savedImageSport2);
        Sport savedSport2 = this.sportRepository.saveAndFlush(validSport2);
        Event validEvent2 = createValidEvent();
        validEvent2.setSport(savedSport2);
        validEventServiceModel2 = this.modelMapper.map(validEvent2, EventServiceModel.class);

        /* CREATE EVENT SERVICE MODEL 3 */
        Image validImage3 = createValidImage();
        Image savedImageSport3 = this.imageRepository.saveAndFlush(validImage3);
        Sport validSport3 = createValidSport();
        validSport3.setIconImage(savedImageSport3);
        Sport savedSport3 = this.sportRepository.saveAndFlush(validSport3);
        Event validEvent3 = createValidEvent();
        validEvent3.setSport(savedSport3);
        validEventServiceModel3 = this.modelMapper.map(validEvent3, EventServiceModel.class);

        /* CREATE SCHEDULE  SERVICE MODEL 4 */
        Schedule validSchedule4 = createValidSchedule();
        SportCenter validSportCenter4 = createValidSportCenter();
        Address address4 = createValidAddress();
        Address savedAddress4 = this.addressRepository.saveAndFlush(address4);
        Image image4 = createValidImage();
        Image savedImage4 = this.imageRepository.saveAndFlush(image4);
        validSportCenter4.setAddress(savedAddress4);
        validSportCenter4.setIconImage(savedImage4);
        SportCenter savedSportCenter4 = this.sportCenterRepository.saveAndFlush(validSportCenter4);
        validSchedule4.setSportCenter(savedSportCenter4);
        validScheduleServiceModel4 = this.modelMapper.map(validSchedule4, ScheduleServiceModel.class);
    }

    /* ScheduleServiceModel createSchedule(SportCenterServiceModel sportCenterServiceModel,
                                               String day, String month, String year)  */
    @Test
    public void createSchedule_whenModelIsValid_successfullyCreated() throws CreateException {

        SportCenter sportCenter = sportCenter = this.modelMapper
                .map(validSportCenterServiceModel1, SportCenter.class);
        SportCenter savedSportCenter = this.sportCenterRepository.saveAndFlush(sportCenter);
        SportCenterServiceModel sportCenterServiceModel = this.modelMapper
                .map(savedSportCenter, SportCenterServiceModel.class);
        ScheduleServiceModel actual = this.scheduleService
                .createSchedule(sportCenterServiceModel, "11", "11", "2011");
        ScheduleServiceModel expected = this.modelMapper
                .map(this.scheduleRepository.findAll().get(0), ScheduleServiceModel.class);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDay(), actual.getDay());
        assertEquals(expected.getMonth(), actual.getMonth());
        assertEquals(expected.getYear(), actual.getYear());
    }

    @Test(expected = Exception.class)
    public void createSchedule_whenModelIsNotValid_throwsException() throws CreateException {

        ScheduleServiceModel actual = this.scheduleService
                .createSchedule(validSportCenterServiceModel1, "32", "13", "3000");
    }


    /* ScheduleServiceModel findByID(String scheduleID)  */
    @Test
    public void findByID_whenModelIsValid_successful() throws CreateException, ReadException {

        SportCenter sportCenter = this.modelMapper
                .map(validSportCenterServiceModel1, SportCenter.class);
        SportCenter savedSportCenter = this.sportCenterRepository.saveAndFlush(sportCenter);
        SportCenterServiceModel sportCenterServiceModel = this.modelMapper
                .map(savedSportCenter, SportCenterServiceModel.class);
        ScheduleServiceModel expected = this.scheduleService
                .createSchedule(sportCenterServiceModel, "11", "11", "2011");
        ScheduleServiceModel actual = this.scheduleService.findByID(expected.getId());

        assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = Exception.class)
    public void findByID_whenModelIsNotValid_throwsException() throws ReadException {

        ScheduleServiceModel actual = this.scheduleService.findByID("wrongId");
    }

    /* ScheduleServiceModel findByDetails(String sportCenterID,
                                              String day, String month, String year)  */
    @Test
    public void findByDetails_whenModelIsValid_successful() throws CreateException, ReadException {

        SportCenter sportCenter = this.modelMapper
                .map(validSportCenterServiceModel1, SportCenter.class);
        SportCenter savedSportCenter = this.sportCenterRepository.saveAndFlush(sportCenter);
        SportCenterServiceModel sportCenterServiceModel = this.modelMapper
                .map(savedSportCenter, SportCenterServiceModel.class);
        ScheduleServiceModel expected = this.scheduleService
                .createSchedule(sportCenterServiceModel, "11", "11", "2011");
        ScheduleServiceModel actual = this.scheduleService
                .findByDetails(sportCenterServiceModel.getId(), "11", "11", "2011");

        assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = Exception.class)
    public void findByDetails_whenModelIsNotValid_throwsException() throws ReadException {

        ScheduleServiceModel actual = this.scheduleService
                .findByDetails("wrongId", "11", "11", "2011");
    }

    /* ScheduleServiceModel addEvent(ScheduleServiceModel scheduleServiceModel,
                                         EventServiceModel eventServiceModel)  */
    @Test
    public void addEvent_whenModelIsValid_successful() throws CreateException, UpdateException {

        /* create and save schedule */
        SportCenter sportCenter = this.modelMapper
                .map(validSportCenterServiceModel1, SportCenter.class);
        SportCenter savedSportCenter = this.sportCenterRepository.saveAndFlush(sportCenter);
        SportCenterServiceModel sportCenterServiceModel = this.modelMapper
                .map(savedSportCenter, SportCenterServiceModel.class);
        ScheduleServiceModel savedScheduleServiceModel = this.scheduleService
                .createSchedule(sportCenterServiceModel, "11", "11", "2011");

        ScheduleServiceModel updatedScheduleServiceModel = this.scheduleService
                .addEvent(savedScheduleServiceModel, validEventServiceModel2);

        assertEquals(savedScheduleServiceModel.getId(), updatedScheduleServiceModel.getId());
        assertEquals(11, updatedScheduleServiceModel.getDay());
        assertEquals(11, updatedScheduleServiceModel.getMonth());
        assertEquals(2011, updatedScheduleServiceModel.getYear());
    }

    @Test(expected = Exception.class)
    public void addEvent_whenModelIsNotValid_throwsException() throws CreateException, UpdateException {

        /* create and save schedule */
        SportCenter sportCenter = this.modelMapper
                .map(validSportCenterServiceModel1, SportCenter.class);
        SportCenter savedSportCenter = this.sportCenterRepository.saveAndFlush(sportCenter);
        SportCenterServiceModel sportCenterServiceModel = this.modelMapper
                .map(savedSportCenter, SportCenterServiceModel.class);
        ScheduleServiceModel savedScheduleServiceModel = this.scheduleService
                .createSchedule(sportCenterServiceModel, "11", "11", "2011");

        validEventServiceModel2.setMaxCapacity(-4);
        ScheduleServiceModel updatedScheduleServiceModel = this.scheduleService
                .addEvent(savedScheduleServiceModel, validEventServiceModel2);

    }


    /*  void updateEvent(ScheduleServiceModel scheduleServiceModel,
                            EventServiceModel eventServiceModel) */

    @Test(expected = Exception.class)
    public void updateEvent_bothArgumentsNull_throwsException() throws UpdateException {

        this.scheduleService.updateEvent(null, null);
    }

    /*   void deleteEvent(ScheduleServiceModel scheduleServiceModel,
                            EventServiceModel eventServiceModel) */
    @Test
    public void deleteEvent_whenModelIsValid_successful() throws UpdateException, DeleteException {

        Event event = this.modelMapper.map(validEventServiceModel3, Event.class);
        Event savedEvent = this.eventRepository.saveAndFlush(event);
        /* add it to default time10 ArrayList<>() */
        Schedule schedule = this.modelMapper.map(validScheduleServiceModel4, Schedule.class);
        schedule.getTime10().add(event);
        Schedule savedSchedule = this.scheduleRepository.saveAndFlush(schedule);

        Schedule initialSchedule = this.scheduleRepository.findById(savedSchedule.getId()).orElse(null);
        int initialTime10Size = initialSchedule.getTime10().size();

        ScheduleServiceModel scheduleServiceModel = this.modelMapper.map(savedSchedule, ScheduleServiceModel.class);
        EventServiceModel eventServiceModel = this.modelMapper.map(savedEvent, EventServiceModel.class);
        this.scheduleService.deleteEvent(scheduleServiceModel, eventServiceModel);

        Schedule actualSchedule = this.scheduleRepository.findById(savedSchedule.getId()).orElse(null);
        int actualTime10Size = actualSchedule.getTime10().size();

        assertNotEquals(initialTime10Size, actualTime10Size);
    }

    @Test(expected = Exception.class)
    public void deleteEvent_whenModelsAreNotValid_throwsException() throws UpdateException, DeleteException {

        Event event = this.modelMapper.map(validEventServiceModel3, Event.class);
        Event savedEvent = this.eventRepository.saveAndFlush(event);

        Schedule schedule = this.modelMapper.map(validScheduleServiceModel4, Schedule.class);
        schedule.getTime10().add(event);
        Schedule savedSchedule = this.scheduleRepository.saveAndFlush(schedule);

        EventServiceModel eventServiceModel = this.modelMapper.map(savedEvent, EventServiceModel.class);
        eventServiceModel.setLevel("");
        ScheduleServiceModel scheduleServiceModel = this.modelMapper.map(savedSchedule, ScheduleServiceModel.class);
        scheduleServiceModel.setDay(32);
        this.scheduleService.deleteEvent(scheduleServiceModel, eventServiceModel);
    }

}
