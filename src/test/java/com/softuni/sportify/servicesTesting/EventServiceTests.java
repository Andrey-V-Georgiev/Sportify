package com.softuni.sportify.servicesTesting;

import com.softuni.sportify.domain.entities.*;
import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.DeleteException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import com.softuni.sportify.repositories.*;
import com.softuni.sportify.services.EventService;
import com.softuni.sportify.services.EventServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.Validator;

import static com.softuni.sportify.factory.AddressFactory.createValidAddress;
import static com.softuni.sportify.factory.EventFactory.createValidEvent;
import static com.softuni.sportify.factory.EventFactory.createValidEventServiceModel;
import static com.softuni.sportify.factory.ImageFactory.createValidImage;
import static com.softuni.sportify.factory.ScheduleFactory.createValidSchedule;
import static com.softuni.sportify.factory.ScheduleFactory.createValidScheduleServiceModel;
import static com.softuni.sportify.factory.SportCenterFactory.createValidSportCenter;
import static com.softuni.sportify.factory.SportFactory.createValidSport;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EventServiceTests {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private SportRepository sportRepository;
    @Autowired
    private SportCenterRepository sportCenterRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private AddressRepository addressRepository;

    private ModelMapper modelMapper = new ModelMapper();
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private EventService eventService;

    private EventServiceModel validEventServiceModel;
    private EventServiceModel validEventServiceModel2;
    private ScheduleServiceModel validScheduleServiceModel;

    @Before
    public void init() {
        this.eventService = new EventServiceImpl(this.modelMapper, this.eventRepository, this.validator);
        /* CREATE AND SAVE EVENT */
        Image validImage = createValidImage();
        Image savedImageSport = this.imageRepository.saveAndFlush(validImage);
        Sport validSport = createValidSport();
        validSport.setIconImage(savedImageSport);
        Sport savedSport = this.sportRepository.saveAndFlush(validSport);
        Event validEvent = createValidEvent();
        validEvent.setSport(savedSport);
        validEventServiceModel = this.modelMapper.map(validEvent, EventServiceModel.class);

        /* CREATE AND SAVE EVENT 2 */
        Image validImage2 = createValidImage();
        Image savedImageSport2 = this.imageRepository.saveAndFlush(validImage2);
        Sport validSport2 = createValidSport();
        validSport2.setIconImage(savedImageSport2);
        Sport savedSport2 = this.sportRepository.saveAndFlush(validSport2);
        Event validEvent2 = createValidEvent();
        validEvent2.setSport(savedSport2);
        validEventServiceModel2 = this.modelMapper.map(validEvent2, EventServiceModel.class);

        /* CREATE AND SAVE SCHEDULE */
        Schedule validSchedule = createValidSchedule();
        SportCenter validSportCenter = createValidSportCenter();
        Address savedAddress = this.addressRepository.saveAndFlush(createValidAddress());
        Image savedImageSC = this.imageRepository.saveAndFlush(createValidImage());
        validSportCenter.setAddress(savedAddress);
        validSportCenter.setIconImage(savedImageSC);
        SportCenter savedSportCenter = this.sportCenterRepository.saveAndFlush(validSportCenter);
        validSchedule.setSportCenter(savedSportCenter);
        validScheduleServiceModel = this.modelMapper.map(validSchedule, ScheduleServiceModel.class);
    }

    @Test
    public void createEvent_whenModelIsValid_eventCreated() throws CreateException {

        EventServiceModel actual = eventService.createEvent(validEventServiceModel, validScheduleServiceModel);
        EventServiceModel expected = this.modelMapper
                .map(this.eventRepository.findAll().get(0), EventServiceModel.class);

        assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = Exception.class)
    public void createEvent_whenModelIsEmpty_throwsException() throws CreateException {

        EventServiceModel eventServiceModel = createValidEventServiceModel();
        ScheduleServiceModel scheduleServiceModel = createValidScheduleServiceModel();

        EventServiceModel actual = eventService.createEvent(eventServiceModel, scheduleServiceModel);
    }

    /*findByID*/
    @Test
    public void findById_whenIdIsValid_correctEvent() throws ReadException {

        Event event = this.modelMapper.map(validEventServiceModel, Event.class);
        Event expected = this.eventRepository.saveAndFlush(event);
        EventServiceModel actual = this.eventService.findByID(expected.getId());

        assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = Exception.class)
    public void findById_whenIdIsNotValid_throwException() throws ReadException {

        EventServiceModel actual = this.eventService.findByID("wringId");
    }

    /*updateEvent(EventServiceModel eventServiceModel)*/

    @Test
    public void updateEvent_whenModelIsValid_correctEditResult() throws  UpdateException {

        Event event = this.modelMapper.map(validEventServiceModel, Event.class);
        Event expectedEvent = this.eventRepository.saveAndFlush(event);
        String expectedLevel = expectedEvent.getLevel();

        validEventServiceModel2.setId(expectedEvent.getId());
        validEventServiceModel2.setLevel("editedLevel");
        EventServiceModel actualEvent = this.eventService.updateEvent(validEventServiceModel2);
        String actualLevel = actualEvent.getLevel();

        assertEquals(expectedEvent.getId(), actualEvent.getId());
        assertEquals(expectedLevel, "Beginner");
        assertEquals(actualLevel, "editedLevel");
    }

    @Test(expected = Exception.class)
    public void updateEvent_whenModelIsNotValid_throwException() throws UpdateException {

        validEventServiceModel.setMaxCapacity(200);
        this.eventService.updateEvent(validEventServiceModel);
    }

    /*deleteEvent(EventServiceModel eventServiceModel)*/

    @Test
    public void deleteEvent_whenModelIsValid_successfulDelete() throws DeleteException {

        Event event = this.modelMapper.map(validEventServiceModel, Event.class);
        Event savedEvent = this.eventRepository.saveAndFlush(event);
        int beforeDbSize = this.eventRepository.findAll().size();

        EventServiceModel eventServiceModel = this.modelMapper.map(savedEvent, EventServiceModel.class);
        this.eventService.deleteEvent(eventServiceModel);

        int expectedDbSize = 0;
        int actualDbSize = this.eventRepository.findAll().size();

        assertEquals(beforeDbSize, 1);
        assertEquals(expectedDbSize, actualDbSize);
    }

    @Test(expected = Exception.class)
    public void deleteEvent_whenModelIsNotValid_throwException() throws DeleteException {

        Event event = this.modelMapper.map(validEventServiceModel, Event.class);
        Event savedEvent = this.eventRepository.saveAndFlush(event);
        EventServiceModel eventServiceModel = this.modelMapper.map(savedEvent, EventServiceModel.class);
        eventServiceModel.setMaxCapacity(300);
        this.eventService.deleteEvent(eventServiceModel);
    }

}
