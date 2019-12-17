package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.DeleteException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {

    ScheduleServiceModel createSchedule(SportCenterServiceModel sportCenterServiceModel,
                                        String day,
                                        String month,
                                        String year) throws CreateException;

    ScheduleServiceModel findByID(String scheduleID) throws ReadException;

    ScheduleServiceModel findByDetails(String sportCenterID,
                                       String day,
                                       String month,
                                       String year) throws ReadException;

    ScheduleServiceModel addEvent(ScheduleServiceModel scheduleServiceModel,
                                  EventServiceModel eventServiceModel) throws UpdateException;

    void updateEvent(ScheduleServiceModel scheduleServiceModel,
                     EventServiceModel eventServiceModel) throws UpdateException;

    void deleteEvent(ScheduleServiceModel scheduleServiceModel,
                     EventServiceModel eventServiceModel) throws DeleteException, UpdateException;

    void deleteScheduleByID(String id) throws DeleteException;

}
