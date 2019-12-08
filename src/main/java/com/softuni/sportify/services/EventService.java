package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    EventServiceModel createEvent(EventServiceModel eventServiceModel, ScheduleServiceModel scheduleServiceModel);

    EventServiceModel findByID(String eventID);

    List<String> findAllLevels();

    List<String> findAllLevelsStartsWith(EventServiceModel eventServiceModel);

    EventServiceModel updateEvent(EventServiceModel eventServiceModel);
}
