package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Event;
import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.repositories.EventRepository;
import com.softuni.sportify.repositories.SportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.EventLevelConstants.*;

@Service
public class EventServiceImpl implements EventService {

    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;
    private final SportRepository sportRepository;

    @Autowired
    public EventServiceImpl(ModelMapper modelMapper,
                            EventRepository eventRepository,
                            SportRepository sportRepository) {
        this.modelMapper = modelMapper;
        this.eventRepository = eventRepository;
        this.sportRepository = sportRepository;
    }

    @Override
    public EventServiceModel createEvent(EventServiceModel eventServiceModel,
                                         ScheduleServiceModel scheduleServiceModel) {

        eventServiceModel.setDayOfMonth(scheduleServiceModel.getDay());
        eventServiceModel.setMonth(scheduleServiceModel.getMonth());
        eventServiceModel.setYear(scheduleServiceModel.getYear());
        Event event = this.eventRepository.saveAndFlush(this.modelMapper.map(eventServiceModel, Event.class));

        return this.modelMapper.map(event, EventServiceModel.class);
    }

    @Override
    public EventServiceModel findByID(String eventID) {

        Event event = this.eventRepository.findById(eventID).orElse(null);
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
    public EventServiceModel updateEvent(EventServiceModel eventServiceModel) {

        Event event = this.modelMapper.map(eventServiceModel, Event.class);
        Event updatedEvent = this.eventRepository.saveAndFlush(event);
        return this.modelMapper.map(updatedEvent, EventServiceModel.class);
    }

    @Override
    public void deleteEvent(EventServiceModel eventServiceModel) {

        Event event = this.modelMapper.map(eventServiceModel, Event.class);
        this.eventRepository.delete(event);
    }
}
