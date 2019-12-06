package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface EventService {
    EventServiceModel createEvent(EventServiceModel eventServiceModel, ScheduleServiceModel scheduleServiceModel);
}
