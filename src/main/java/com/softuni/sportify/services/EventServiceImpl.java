package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Event;
import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.repositories.EventRepository;
import com.softuni.sportify.repositories.SportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
