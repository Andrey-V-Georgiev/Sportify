package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.DeleteException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    EventServiceModel createEvent(EventServiceModel eventServiceModel, ScheduleServiceModel scheduleServiceModel) throws CreateException;

    EventServiceModel findByID(String eventID) throws ReadException;

    List<String> findAllLevels();

    List<String> findAllLevelsStartsWith(EventServiceModel eventServiceModel);

    EventServiceModel updateEvent(EventServiceModel eventServiceModel) throws UpdateException;

    void deleteEvent(EventServiceModel eventServiceModel) throws DeleteException;

    void deleteAllBySport(SportServiceModel sportServiceModel) throws DeleteException;

}
