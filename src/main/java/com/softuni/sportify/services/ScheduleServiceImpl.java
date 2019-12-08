package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Event;
import com.softuni.sportify.domain.entities.Schedule;
import com.softuni.sportify.domain.entities.SportCenter;
import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import com.softuni.sportify.repositories.ScheduleRepository;
import com.softuni.sportify.repositories.SportCenterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.EventHoursConstants.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;
    private final SportCenterRepository sportCenterRepository;
    private final EventService eventService;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
                               ModelMapper modelMapper,
                               SportCenterRepository sportCenterRepository,
                               EventService eventService) {
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = modelMapper;
        this.sportCenterRepository = sportCenterRepository;
        this.eventService = eventService;
    }

    @Override
    public ScheduleServiceModel createSchedule(SportCenterServiceModel sportCenterServiceModel, String day, String month, String year) {

        SportCenter sportCenter = this.modelMapper.map(sportCenterServiceModel, SportCenter.class);
        ScheduleServiceModel scheduleServiceModel = new ScheduleServiceModel();

        scheduleServiceModel.setSportCenter(this.modelMapper.map(sportCenter, SportCenterServiceModel.class));
        scheduleServiceModel.setDay(Integer.parseInt(day));
        scheduleServiceModel.setMonth(Integer.parseInt(month));
        scheduleServiceModel.setYear(Integer.parseInt(year));

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
        try {
            sportCenter.getCalendar().add(schedule);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.sportCenterRepository.save(sportCenter);

        return this.modelMapper.map(schedule, ScheduleServiceModel.class);
    }

    @Override
    public ScheduleServiceModel findByID(String scheduleID) {

        Schedule schedule = this.scheduleRepository.findById(scheduleID).orElse(null);
        return this.modelMapper.map(schedule, ScheduleServiceModel.class);
    }

    @Override
    public ScheduleServiceModel findByDetails(String sportCenterID, String day, String month, String year) {

        SportCenter sportCenter = this.sportCenterRepository.findById(sportCenterID).orElse(null);
        Schedule schedule = null;
        try {
            schedule = sportCenter.getCalendar()
                    .stream()
                    .filter(s -> Integer.parseInt(day) == (s.getDay()) &&
                            Integer.parseInt(month) == (s.getMonth()) &&
                            Integer.parseInt(year) == (s.getYear()))
                    .collect(Collectors.toList())
                    .get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.modelMapper.map(schedule, ScheduleServiceModel.class);
    }

    @Override
    public ScheduleServiceModel addEvent(ScheduleServiceModel scheduleServiceModel,
                                         EventServiceModel eventServiceModel) {

        switch (eventServiceModel.getStartTime()) {
            case SIX_OCLOCK:
                scheduleServiceModel.getTime6().add(eventServiceModel);
                break;
            case SEVEN_OCLOCK:
                scheduleServiceModel.getTime7().add(eventServiceModel);
                break;
            case EIGHT_OCLOCK:
                scheduleServiceModel.getTime8().add(eventServiceModel);
                break;
            case NINE_OCLOCK:
                scheduleServiceModel.getTime9().add(eventServiceModel);
                break;
            case TEN_OCLOCK:
                scheduleServiceModel.getTime10().add(eventServiceModel);
                break;
            case ELEVEN_OCLOCK:
                scheduleServiceModel.getTime11().add(eventServiceModel);
                break;
            case TWELVE_OCLOCK:
                scheduleServiceModel.getTime12().add(eventServiceModel);
                break;
            case THIRTEEN_OCLOCK:
                scheduleServiceModel.getTime13().add(eventServiceModel);
                break;
            case FOURTEEN_OCLOCK:
                scheduleServiceModel.getTime14().add(eventServiceModel);
                break;
            case FIFTEEN_OCLOCK:
                scheduleServiceModel.getTime15().add(eventServiceModel);
                break;
            case SIXTEEN_OCLOCK:
                scheduleServiceModel.getTime16().add(eventServiceModel);
                break;
            case SEVENTEEN_OCLOCK:
                scheduleServiceModel.getTime17().add(eventServiceModel);
                break;
            case EIGHTEEN_OCLOCK:
                scheduleServiceModel.getTime18().add(eventServiceModel);
                break;
            case NINETEEN_OCLOCK:
                scheduleServiceModel.getTime19().add(eventServiceModel);
                break;
            case TWENTY_OCLOCK:
                scheduleServiceModel.getTime20().add(eventServiceModel);
                break;
            case TWENTYONE_OCLOCK:
                scheduleServiceModel.getTime21().add(eventServiceModel);
                break;
            case TWENTYTWO_OCLOCK:
                scheduleServiceModel.getTime22().add(eventServiceModel);
                break;
        }
        Schedule schedule = this.scheduleRepository.saveAndFlush(
                this.modelMapper.map(scheduleServiceModel, Schedule.class));

        return this.modelMapper.map(schedule, ScheduleServiceModel.class);
    }

    @Override
    public void updateEvent(ScheduleServiceModel scheduleServiceModel,
                            EventServiceModel eventServiceModel) {

        EventServiceModel updatedEventServiceModel = this.eventService.updateEvent(eventServiceModel);
        switch (eventServiceModel.getStartTime()) {
            case SIX_OCLOCK:
                scheduleServiceModel.setTime6(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
            break;
            case SEVEN_OCLOCK:
                scheduleServiceModel.setTime7(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
            case EIGHT_OCLOCK:
                scheduleServiceModel.setTime8(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
            case NINE_OCLOCK:
                scheduleServiceModel.setTime9(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
            case TEN_OCLOCK:
                scheduleServiceModel.setTime10(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
            case ELEVEN_OCLOCK:
                scheduleServiceModel.setTime11(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
            case TWELVE_OCLOCK:
                scheduleServiceModel.setTime12(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
            case THIRTEEN_OCLOCK:
                scheduleServiceModel.setTime13(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
            case FOURTEEN_OCLOCK:
                scheduleServiceModel.setTime14(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
            case FIFTEEN_OCLOCK:
                scheduleServiceModel.setTime15(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
            case SIXTEEN_OCLOCK:
                scheduleServiceModel.setTime16(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
            case SEVENTEEN_OCLOCK:
                scheduleServiceModel.setTime17(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
            case EIGHTEEN_OCLOCK:
                scheduleServiceModel.setTime18(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
            case NINETEEN_OCLOCK:
                scheduleServiceModel.setTime19(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
            case TWENTY_OCLOCK:
                scheduleServiceModel.setTime20(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
            case TWENTYONE_OCLOCK:
                scheduleServiceModel.setTime21(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
            case TWENTYTWO_OCLOCK:
                scheduleServiceModel.setTime22(updateEventIInList(scheduleServiceModel, updatedEventServiceModel));
                break;
        }
    }

    private List<EventServiceModel> updateEventIInList(ScheduleServiceModel scheduleServiceModel,
                                                       EventServiceModel updatedEventServiceModel) {

        List<EventServiceModel> filteredEvents = scheduleServiceModel.getTime6()
                .stream()
                .filter(e -> !e.getId().equals(updatedEventServiceModel.getId()))
                .collect(Collectors.toList());
        filteredEvents.add(updatedEventServiceModel);
        return filteredEvents;
    }


}
