package com.softuni.sportify.servicesTesting;

import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.repositories.EventRepository;
import com.softuni.sportify.services.EventService;
import com.softuni.sportify.services.EventServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventServiceTests {

    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Before
    public void init() {
        this.eventService = new EventServiceImpl(this.modelMapper, this.eventRepository, this.validator);
    }


    @Test(expected = CreateException.class)
    public void createEvent_whenEventServiceModelIsNotValid_throwsCreateException() throws CreateException {

        EventServiceModel eventServiceModel = new EventServiceModel();
        eventServiceModel.setSport(new SportServiceModel());
        eventServiceModel.setLevel("Beginner");
        eventServiceModel.setFloor(11);
        eventServiceModel.setHall("Aula");
        eventServiceModel.setMaxCapacity(50);

        ScheduleServiceModel scheduleServiceModel = new ScheduleServiceModel();
        scheduleServiceModel.setDay(3);
        scheduleServiceModel.setMonth(3);
        scheduleServiceModel.setYear(2020);

        eventService.createEvent(eventServiceModel, scheduleServiceModel);
    }

}
