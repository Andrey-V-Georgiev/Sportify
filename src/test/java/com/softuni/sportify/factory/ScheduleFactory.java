package com.softuni.sportify.factory;

import com.softuni.sportify.domain.entities.Schedule;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;

import java.util.ArrayList;

import static com.softuni.sportify.factory.SportCenterFactory.createValidSportCenter;
import static com.softuni.sportify.factory.SportCenterFactory.createValidSportCenterServiceModel;

public abstract class ScheduleFactory {

    public static ScheduleServiceModel createValidScheduleServiceModel() {

        ScheduleServiceModel scheduleServiceModel = new ScheduleServiceModel();


        scheduleServiceModel.setDay(3);
        scheduleServiceModel.setMonth(3);
        scheduleServiceModel.setYear(2020);
        scheduleServiceModel.setSportCenter(createValidSportCenterServiceModel());
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

        return scheduleServiceModel;
    }

    public static Schedule createValidSchedule() {

        Schedule schedule = new Schedule();
        schedule.setDay(3);
        schedule.setMonth(3);
        schedule.setYear(2020);
        schedule.setSportCenter(createValidSportCenter());
        schedule.setTime6(new ArrayList<>());
        schedule.setTime7(new ArrayList<>());
        schedule.setTime8(new ArrayList<>());
        schedule.setTime9(new ArrayList<>());
        schedule.setTime10(new ArrayList<>());
        schedule.setTime11(new ArrayList<>());
        schedule.setTime12(new ArrayList<>());
        schedule.setTime13(new ArrayList<>());
        schedule.setTime14(new ArrayList<>());
        schedule.setTime15(new ArrayList<>());
        schedule.setTime16(new ArrayList<>());
        schedule.setTime17(new ArrayList<>());
        schedule.setTime18(new ArrayList<>());
        schedule.setTime19(new ArrayList<>());
        schedule.setTime20(new ArrayList<>());
        schedule.setTime21(new ArrayList<>());
        schedule.setTime22(new ArrayList<>());

        return schedule;
    }
}
