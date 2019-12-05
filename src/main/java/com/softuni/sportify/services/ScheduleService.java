package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {

    ScheduleServiceModel createSchedule(String sportCenterID, String dayOfMonth, String yearOfMonth);
}
