package com.softuni.sportify.services;

import com.softuni.sportify.domain.entities.Schedule;
import com.softuni.sportify.domain.entities.SportCenter;
import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import com.softuni.sportify.repositories.ScheduleRepository;
import com.softuni.sportify.repositories.SportCenterRepository;
import com.softuni.sportify.exceptions.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.softuni.sportify.constants.EventHoursConstants.*;
import static com.softuni.sportify.constants.ExceptionConstants.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;
    private final SportCenterRepository sportCenterRepository;
    private final EventService eventService;
    private final Validator validator;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
                               ModelMapper modelMapper,
                               SportCenterRepository sportCenterRepository,
                               EventService eventService,
                               Validator validator) {
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = modelMapper;
        this.sportCenterRepository = sportCenterRepository;
        this.eventService = eventService;
        this.validator = validator;
    }

    @Override
    public ScheduleServiceModel createSchedule(SportCenterServiceModel sportCenterServiceModel,
                                               String day, String month, String year) throws CreateException {

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

        if (!validator.validate(scheduleServiceModel).isEmpty() ) {
            throw new CreateException(SCHEDULE_CREATE_EXCEPTION_MSG);
        }
        Schedule schedule = this.modelMapper.map(scheduleServiceModel, Schedule.class);
        Schedule savedSchedule = this.scheduleRepository.saveAndFlush(schedule);
        sportCenter.getCalendar().add(schedule);
        this.sportCenterRepository.save(sportCenter);

        return this.modelMapper.map(schedule, ScheduleServiceModel.class);
    }

    @Override
    public ScheduleServiceModel findByID(String scheduleID) throws ReadException {

        Schedule schedule = this.scheduleRepository.findById(scheduleID).orElse(null);
        ScheduleServiceModel scheduleServiceModel = this.modelMapper.map(schedule, ScheduleServiceModel.class);
        if (!validator.validate(scheduleServiceModel).isEmpty()) {
            throw new ReadException(SCHEDULE_READ_EXCEPTION_MSG);
        }
        return scheduleServiceModel;
    }

    @Override
    public ScheduleServiceModel findByDetails(String sportCenterID, String day, String month, String year) throws ReadException {

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
            throw new ReadException(SCHEDULE_READ_EXCEPTION_MSG);
        }
        return this.modelMapper.map(schedule, ScheduleServiceModel.class);
    }

    @Override
    public ScheduleServiceModel addEvent(ScheduleServiceModel scheduleServiceModel,
                                         EventServiceModel eventServiceModel) throws UpdateException {
        if (!validator.validate(scheduleServiceModel).isEmpty() || !validator.validate(eventServiceModel).isEmpty()) {
            throw new UpdateException(SCHEDULE_UPDATE_EXCEPTION_MSG);
        }
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
                            EventServiceModel eventServiceModel) throws UpdateException {

        if (!validator.validate(scheduleServiceModel).isEmpty() || !validator.validate(eventServiceModel).isEmpty()) {
            throw new UpdateException(SCHEDULE_UPDATE_EXCEPTION_MSG);
        }
        EventServiceModel updatedEventServiceModel = this.eventService.updateEvent(eventServiceModel);

        switch (eventServiceModel.getStartTime()) {
            case SIX_OCLOCK:
                scheduleServiceModel.setTime6(updateEventInList(scheduleServiceModel.getTime6(), updatedEventServiceModel));
                break;
            case SEVEN_OCLOCK:
                scheduleServiceModel.setTime7(updateEventInList(scheduleServiceModel.getTime7(), updatedEventServiceModel));
                break;
            case EIGHT_OCLOCK:
                scheduleServiceModel.setTime8(updateEventInList(scheduleServiceModel.getTime8(), updatedEventServiceModel));
                break;
            case NINE_OCLOCK:
                scheduleServiceModel.setTime9(updateEventInList(scheduleServiceModel.getTime9(), updatedEventServiceModel));
                break;
            case TEN_OCLOCK:
                scheduleServiceModel.setTime10(updateEventInList(scheduleServiceModel.getTime10(), updatedEventServiceModel));
                break;
            case ELEVEN_OCLOCK:
                scheduleServiceModel.setTime11(updateEventInList(scheduleServiceModel.getTime11(), updatedEventServiceModel));
                break;
            case TWELVE_OCLOCK:
                scheduleServiceModel.setTime12(updateEventInList(scheduleServiceModel.getTime12(), updatedEventServiceModel));
                break;
            case THIRTEEN_OCLOCK:
                scheduleServiceModel.setTime13(updateEventInList(scheduleServiceModel.getTime13(), updatedEventServiceModel));
                break;
            case FOURTEEN_OCLOCK:
                scheduleServiceModel.setTime14(updateEventInList(scheduleServiceModel.getTime14(), updatedEventServiceModel));
                break;
            case FIFTEEN_OCLOCK:
                scheduleServiceModel.setTime15(updateEventInList(scheduleServiceModel.getTime15(), updatedEventServiceModel));
                break;
            case SIXTEEN_OCLOCK:
                scheduleServiceModel.setTime16(updateEventInList(scheduleServiceModel.getTime16(), updatedEventServiceModel));
                break;
            case SEVENTEEN_OCLOCK:
                scheduleServiceModel.setTime17(updateEventInList(scheduleServiceModel.getTime17(), updatedEventServiceModel));
                break;
            case EIGHTEEN_OCLOCK:
                scheduleServiceModel.setTime18(updateEventInList(scheduleServiceModel.getTime18(), updatedEventServiceModel));
                break;
            case NINETEEN_OCLOCK:
                scheduleServiceModel.setTime19(updateEventInList(scheduleServiceModel.getTime19(), updatedEventServiceModel));
                break;
            case TWENTY_OCLOCK:
                scheduleServiceModel.setTime20(updateEventInList(scheduleServiceModel.getTime20(), updatedEventServiceModel));
                break;
            case TWENTYONE_OCLOCK:
                scheduleServiceModel.setTime21(updateEventInList(scheduleServiceModel.getTime21(), updatedEventServiceModel));
                break;
            case TWENTYTWO_OCLOCK:
                scheduleServiceModel.setTime22(updateEventInList(scheduleServiceModel.getTime22(), updatedEventServiceModel));
                break;
        }
        this.scheduleRepository.save(this.modelMapper.map(scheduleServiceModel, Schedule.class));
    }

    @Override
    public void deleteEvent(ScheduleServiceModel scheduleServiceModel,
                            EventServiceModel eventServiceModel) throws DeleteException, UpdateException {

        if (!validator.validate(scheduleServiceModel).isEmpty() || !validator.validate(eventServiceModel).isEmpty()) {
            throw new DeleteException(SCHEDULE_DELETE_EXCEPTION_MSG);
        }
        switch (eventServiceModel.getStartTime()) {
            case SIX_OCLOCK:
                scheduleServiceModel.setTime6(deleteEventFromList(scheduleServiceModel.getTime6(), eventServiceModel));
                break;
            case SEVEN_OCLOCK:
                scheduleServiceModel.setTime7(deleteEventFromList(scheduleServiceModel.getTime7(), eventServiceModel));
                break;
            case EIGHT_OCLOCK:
                scheduleServiceModel.setTime8(deleteEventFromList(scheduleServiceModel.getTime8(), eventServiceModel));
                break;
            case NINE_OCLOCK:
                scheduleServiceModel.setTime9(deleteEventFromList(scheduleServiceModel.getTime9(), eventServiceModel));
                break;
            case TEN_OCLOCK:
                scheduleServiceModel.setTime10(deleteEventFromList(scheduleServiceModel.getTime10(), eventServiceModel));
                break;
            case ELEVEN_OCLOCK:
                scheduleServiceModel.setTime11(deleteEventFromList(scheduleServiceModel.getTime11(), eventServiceModel));
                break;
            case TWELVE_OCLOCK:
                scheduleServiceModel.setTime12(deleteEventFromList(scheduleServiceModel.getTime12(), eventServiceModel));
                break;
            case THIRTEEN_OCLOCK:
                scheduleServiceModel.setTime13(deleteEventFromList(scheduleServiceModel.getTime13(), eventServiceModel));
                break;
            case FOURTEEN_OCLOCK:
                scheduleServiceModel.setTime14(deleteEventFromList(scheduleServiceModel.getTime14(), eventServiceModel));
                break;
            case FIFTEEN_OCLOCK:
                scheduleServiceModel.setTime15(deleteEventFromList(scheduleServiceModel.getTime15(), eventServiceModel));
                break;
            case SIXTEEN_OCLOCK:
                scheduleServiceModel.setTime16(deleteEventFromList(scheduleServiceModel.getTime16(), eventServiceModel));
                break;
            case SEVENTEEN_OCLOCK:
                scheduleServiceModel.setTime17(deleteEventFromList(scheduleServiceModel.getTime17(), eventServiceModel));
                break;
            case EIGHTEEN_OCLOCK:
                scheduleServiceModel.setTime18(deleteEventFromList(scheduleServiceModel.getTime18(), eventServiceModel));
                break;
            case NINETEEN_OCLOCK:
                scheduleServiceModel.setTime19(deleteEventFromList(scheduleServiceModel.getTime19(), eventServiceModel));
                break;
            case TWENTY_OCLOCK:
                scheduleServiceModel.setTime20(deleteEventFromList(scheduleServiceModel.getTime20(), eventServiceModel));
                break;
            case TWENTYONE_OCLOCK:
                scheduleServiceModel.setTime21(deleteEventFromList(scheduleServiceModel.getTime21(), eventServiceModel));
                break;
            case TWENTYTWO_OCLOCK:
                scheduleServiceModel.setTime22(deleteEventFromList(scheduleServiceModel.getTime22(), eventServiceModel));
                break;
        }
        this.scheduleRepository.save(this.modelMapper.map(scheduleServiceModel, Schedule.class));
    }

    @Override
    public void deleteScheduleByID(String id) throws DeleteException {

        try {
            this.scheduleRepository.deleteById(id);
        } catch (Exception e) {
            throw new DeleteException(SCHEDULE_DELETE_EXCEPTION_MSG);
        }
    }

    private List<EventServiceModel> deleteEventFromList(List<EventServiceModel> eventServiceModelList,
                                                        EventServiceModel eventServiceModel)
            throws UpdateException, DeleteException {

        if (!validator.validate(eventServiceModel).isEmpty()) {
            throw new UpdateException(SCHEDULE_UPDATE_EXCEPTION_MSG);
        }
        this.eventService.deleteEvent(eventServiceModel);
        List<EventServiceModel> filteredEventServiceModels = eventServiceModelList
                .stream()
                .filter(e -> !e.getId().equals(eventServiceModel.getId()))
                .collect(Collectors.toList());
        return filteredEventServiceModels;
    }

    private List<EventServiceModel> updateEventInList(List<EventServiceModel> eventServiceModelList,
                                                      EventServiceModel updatedEventServiceModel) throws UpdateException {

        if (!validator.validate(updatedEventServiceModel).isEmpty()) {
            throw new UpdateException(SCHEDULE_UPDATE_EXCEPTION_MSG);
        }
        List<EventServiceModel> filteredEvents = eventServiceModelList
                .stream()
                .filter(e -> !e.getId().equals(updatedEventServiceModel.getId()))
                .collect(Collectors.toList());
        filteredEvents.add(updatedEventServiceModel);
        return filteredEvents;
    }
}
