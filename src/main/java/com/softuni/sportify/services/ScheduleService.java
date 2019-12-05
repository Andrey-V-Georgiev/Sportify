package com.softuni.sportify.services;

import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {

    ScheduleServiceModel createSchedule(SportCenterServiceModel sportCenterServiceModel,
                                        String day,
                                        String month,
                                        String year);
}
