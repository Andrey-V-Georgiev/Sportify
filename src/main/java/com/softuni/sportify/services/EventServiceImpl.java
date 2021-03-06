package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.BaseEntity;
import com.softuni.sportify.domain.entities.Event;
import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.exceptions.*;
import com.softuni.sportify.repositories.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.EventLevelConstants.*;
import static com.softuni.sportify.constants.ExceptionConstants.*;

@Service
public class EventServiceImpl implements EventService {

    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;
    private final Validator validator;

    @Autowired
    public EventServiceImpl(ModelMapper modelMapper,
                            EventRepository eventRepository,
                            Validator validator) {
        this.modelMapper = modelMapper;
        this.eventRepository = eventRepository;
        this.validator = validator;
    }

    @Override
    public EventServiceModel createEvent(EventServiceModel eventServiceModel,
                                         ScheduleServiceModel scheduleServiceModel) throws CreateException {

        eventServiceModel.setDayOfMonth(scheduleServiceModel.getDay());
        eventServiceModel.setMonth(scheduleServiceModel.getMonth());
        eventServiceModel.setYear(scheduleServiceModel.getYear());
        Set<ConstraintViolation<EventServiceModel>> validate = validator.validate(eventServiceModel);

        if(!validate.isEmpty()) {
            throw new CreateException(EVENT_CREATE_EXCEPTION_MSG);
        }
        Event event = this.modelMapper.map(eventServiceModel, Event.class);
        Event savedEvent = savedEvent = this.eventRepository.saveAndFlush(event);

        return this.modelMapper.map(savedEvent, EventServiceModel.class);
    }

    @Override
    public EventServiceModel findByID(String eventID) throws ReadException {

        Event event = this.eventRepository.findById(eventID)
                .orElseThrow(()-> new ReadException(EVENT_READ_EXCEPTION_MSG));
        EventServiceModel eventServiceModel = this.modelMapper.map(event, EventServiceModel.class);
        return this.modelMapper.map(event, EventServiceModel.class);
    }

    @Override
    public List<String> findAllLevels() {

        return new ArrayList<>(Arrays.asList(BEGINNER, MEDIUM, ADVANCED, PROFESSIONAL));
    }

    @Override
    public List<String> findAllLevelsStartsWith(EventServiceModel eventServiceModel) {

        String eventLevel = eventServiceModel.getLevel();
        List<String> allLevels = new ArrayList<>(Arrays.asList(BEGINNER, MEDIUM, ADVANCED, PROFESSIONAL));
        Comparator<String> startsWithLevel = Comparator.comparing(lev -> !lev.equals(eventLevel));
        List<String> sortedLevels = allLevels
                .stream()
                .sorted(startsWithLevel)
                .collect(Collectors.toList());

        return sortedLevels;
    }

    @Override
    public EventServiceModel updateEvent(EventServiceModel eventServiceModel) throws UpdateException {

        if(!validator.validate(eventServiceModel).isEmpty()) {
            throw new UpdateException(EVENT_UPDATE_EXCEPTION_MSG);
        }
        Event event = this.modelMapper.map(eventServiceModel, Event.class);
        Event updatedEvent = this.eventRepository.saveAndFlush(event);
        return this.modelMapper.map(updatedEvent, EventServiceModel.class);
    }

    @Override
    public void deleteEvent(EventServiceModel eventServiceModel) throws DeleteException {

        if(!validator.validate(eventServiceModel).isEmpty()) {
            throw new DeleteException(EVENT_DELETE_EXCEPTION_MSG);
        }
        Event event = this.modelMapper.map(eventServiceModel, Event.class);
        this.eventRepository.delete(event);
    }

    @Override
    public void deleteAllBySport(SportServiceModel sportServiceModel) throws DeleteException {

        if(!validator.validate(sportServiceModel).isEmpty()) {
            throw new DeleteException(EVENT_DELETE_EXCEPTION_MSG);
        }
        List<String> eventsIDs = this.eventRepository.findAll()
                .stream()
                .filter(e -> e.getSport().getId().equals(sportServiceModel.getId()))
                .map(BaseEntity::getId)
                .collect(Collectors.toList());

        for (String id : eventsIDs) {
            this.eventRepository.deleteById(id);
        }
    }
}
