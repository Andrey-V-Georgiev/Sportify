package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Schedule;
import com.softuni.sportify.domain.entities.SportCenter;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import com.softuni.sportify.repositories.ScheduleRepository;
import com.softuni.sportify.repositories.SportCenterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;
    private final SportCenterRepository sportCenterRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
                               ModelMapper modelMapper,
                               SportCenterRepository sportCenterRepository) {
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = modelMapper;
        this.sportCenterRepository = sportCenterRepository;
    }

    @Override
    public ScheduleServiceModel createSchedule(String sportCenterID, String dayOfMonth, String yearOfMonth) {

        SportCenter sportCenter = this.sportCenterRepository.findById(sportCenterID).orElse(null);
        ScheduleServiceModel scheduleServiceModel = new ScheduleServiceModel();

        scheduleServiceModel.setSportCenter(this.modelMapper.map(sportCenter, SportCenterServiceModel.class));
        scheduleServiceModel.setMonth(Integer.parseInt(dayOfMonth));
        scheduleServiceModel.setYear(Integer.parseInt(yearOfMonth));

        scheduleServiceModel.setTime6(new ArrayList<>());
        scheduleServiceModel.setTime7(new ArrayList<>());
        scheduleServiceModel.setTime8(new ArrayList<>());
        scheduleServiceModel.setTime9(new ArrayList<>());
        scheduleServiceModel.setTime10(new ArrayList<>());
        scheduleServiceModel.setTime11(new ArrayList<>());
        scheduleServiceModel.setTime12(new ArrayList<>());
        scheduleServiceModel.setTime13(new ArrayList<>());
        scheduleServiceModel.setTime14(new ArrayList<>());
        scheduleServiceModel.setTime15(new ArrayList<>());
        scheduleServiceModel.setTime16(new ArrayList<>());
        scheduleServiceModel.setTime17(new ArrayList<>());
        scheduleServiceModel.setTime18(new ArrayList<>());
        scheduleServiceModel.setTime19(new ArrayList<>());
        scheduleServiceModel.setTime20(new ArrayList<>());
        scheduleServiceModel.setTime21(new ArrayList<>());
        scheduleServiceModel.setTime22(new ArrayList<>());

        Schedule schedule = this.scheduleRepository.saveAndFlush(
                this.modelMapper.map(scheduleServiceModel, Schedule.class));
        sportCenter.getCalendar().add(schedule);
        this.sportCenterRepository.save(sportCenter);

        return this.modelMapper.map(schedule, ScheduleServiceModel.class);
    }
}
