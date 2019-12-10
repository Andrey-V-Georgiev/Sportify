package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {

    ScheduleServiceModel createSchedule(SportCenterServiceModel sportCenterServiceModel,
                                        String day,
                                        String month,
                                        String year);

    ScheduleServiceModel findByID(String scheduleID);

    ScheduleServiceModel findByDetails(String sportCenterID,
                                       String day,
                                       String month,
                                       String year);

    ScheduleServiceModel addEvent(ScheduleServiceModel scheduleServiceModel,
                                  EventServiceModel eventServiceModel);

    void updateEvent(ScheduleServiceModel scheduleServiceModel,
                     EventServiceModel eventServiceModel);

    void deleteEvent(ScheduleServiceModel scheduleServiceModel,
                     EventServiceModel eventServiceModel);

    void deleteSchedule(ScheduleServiceModel scheduleServiceModel);

    void deleteScheduleByID(String id);

    void deleteSchedulesByIDs(List<String> schedulesIDs);

}
