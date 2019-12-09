package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.EventCreateBindingModel;
import com.softuni.sportify.domain.models.binding_models.EventEditBindingModel;
import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import com.softuni.sportify.domain.models.view_models.ScheduleViewModel;
import com.softuni.sportify.services.EventService;
import com.softuni.sportify.services.ScheduleService;
import com.softuni.sportify.services.SportCenterService;
import com.softuni.sportify.services.SportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.softuni.sportify.constants.AuthConstants.HAS_ROLE_ADMIN;
import static com.softuni.sportify.constants.CalendarControllerConstants.*;
import static com.softuni.sportify.constants.EventHoursConstants.*;
import static com.softuni.sportify.constants.MonthStrings.MONTH_STRINGS;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final SportCenterService sportCenterService;
    private final ModelMapper modelMapper;
    private final ScheduleService scheduleService;
    private final SportService sportService;
    private final EventService eventService;

    @Autowired
    public CalendarController(SportCenterService sportCenterService,
                              ModelMapper modelMapper,
                              ScheduleService scheduleService,
                              SportService sportService,
                              EventService eventService) {
        this.sportCenterService = sportCenterService;
        this.modelMapper = modelMapper;
        this.scheduleService = scheduleService;
        this.sportService = sportService;
        this.eventService = eventService;
    }

    @GetMapping("/show-calendar/{id}")
    public ModelAndView showCalendar(
            @PathVariable("id") String sportCenterID,
            ModelAndView modelAndView) {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);
        modelAndView.addObject("sportCenterServiceModel", sportCenterServiceModel);
        modelAndView.setViewName(VIEW_CALENDAR);
        return modelAndView;
    }

    @GetMapping("/create-schedule/{scID}/{day}/{month}/{year}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView createSchedule(
            @PathVariable("scID") String sportCenterID,
            @PathVariable("day") String day,
            @PathVariable("month") String month,
            @PathVariable("year") String year,
            ModelAndView modelAndView) {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);
        ScheduleServiceModel scheduleServiceModel = this.scheduleService
                .createSchedule(sportCenterServiceModel, day, month, year);
        String scheduleID = scheduleServiceModel.getId();

        modelAndView.setViewName(REDIRECT_TO_SCHEDULE_DETAILS_BY_ID + scheduleID);
        return modelAndView;
    }

    @GetMapping("/schedule-details-by-id/{scheduleID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView scheduleDetailsByID(
            @PathVariable("scheduleID") String scheduleID,
            ModelAndView modelAndView) {

        ScheduleServiceModel scheduleServiceModel = this.scheduleService.findByID(scheduleID);
        ScheduleViewModel scheduleViewModel = this.modelMapper.map(scheduleServiceModel, ScheduleViewModel.class);

        modelAndView.addObject("scheduleViewModel", scheduleViewModel);
        modelAndView.addObject("EVENT_HOURS", EVENT_HOURS);
        modelAndView.addObject("month", MONTH_STRINGS.get(scheduleViewModel.getMonth() - 1));
        modelAndView.setViewName(VIEW_SCHEDULE_DETAILS);
        return modelAndView;
    }

    @GetMapping("/schedule-details/{scID}/{day}/{month}/{year}")
    public ModelAndView editScheduleByDetails(
            @PathVariable("scID") String sportCenterID,
            @PathVariable("day") String day,
            @PathVariable("month") String month,
            @PathVariable("year") String year,
            ModelAndView modelAndView) {

        ScheduleServiceModel scheduleServiceModel = this.scheduleService
                .findByDetails(sportCenterID, day, month, year);
        ScheduleViewModel scheduleViewModel = this.modelMapper.map(scheduleServiceModel, ScheduleViewModel.class);

        modelAndView.addObject("scheduleViewModel", scheduleViewModel);
        modelAndView.addObject("EVENT_HOURS", EVENT_HOURS);
        modelAndView.addObject("month", MONTH_STRINGS.get(scheduleViewModel.getMonth() - 1));
        modelAndView.setViewName(VIEW_SCHEDULE_DETAILS);
        return modelAndView;
    }

    @GetMapping("/create-event/{scheduleID}/{hourStr}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView createScheduleEvent(
            @PathVariable("scheduleID") String scheduleID,
            @PathVariable("hourStr") String hourStr,
            ModelAndView modelAndView) {

        modelAndView.addObject("scheduleID", scheduleID);
        /* for the hidden input - important */
        modelAndView.addObject("hourStr", hourStr);
        modelAndView.addObject("sportsNames", this.sportService.findAllSportsNames());
        modelAndView.addObject("eventLevels", this.eventService.findAllLevels());

        modelAndView.setViewName(VIEW_CREATE_EVENT);
        return modelAndView;
    }

    @PostMapping("/create-event/{scheduleID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView createScheduleEventConfirm(
            @PathVariable("scheduleID") String scheduleID,
            @ModelAttribute EventCreateBindingModel eventCreateBindingModel,
            ModelAndView modelAndView) {

        ScheduleServiceModel scheduleServiceModel = this.scheduleService.findByID(scheduleID);
        EventServiceModel eventServiceModel = this.modelMapper
                .map(eventCreateBindingModel, EventServiceModel.class);
        eventServiceModel.setSport(this.sportService.findByName(eventCreateBindingModel.getSport()));
        EventServiceModel savedEventServiceModel = this.eventService
                .createEvent(eventServiceModel, scheduleServiceModel);
        this.scheduleService.addEvent(scheduleServiceModel, savedEventServiceModel);

        String sportCenterID = scheduleServiceModel.getSportCenter().getId();
        modelAndView.setViewName(REDIRECT_TO_SCHEDULE_DETAILS_BY_ID + scheduleID);
        return modelAndView;
    }

    @GetMapping("/edit-event/{scheduleID}/{eventID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editScheduleEvent(
            @PathVariable("scheduleID") String scheduleID,
            @PathVariable("eventID") String eventID,
            ModelAndView modelAndView) {

        ScheduleServiceModel scheduleServiceModel = this.scheduleService.findByID(scheduleID);
        EventServiceModel eventServiceModel = this.eventService.findByID(eventID);
        EventEditBindingModel eventEditBindingModel = this.modelMapper
                .map(eventServiceModel, EventEditBindingModel.class);

        modelAndView.addObject("scheduleServiceModel", scheduleServiceModel);
        modelAndView.addObject("eventEditBindingModel", eventEditBindingModel);
        modelAndView.addObject("sportsNames",
                this.sportService.findAllSportsNamesStartsWith(eventServiceModel.getSport()));
        modelAndView.addObject("eventLevels",
                this.eventService.findAllLevelsStartsWith(eventServiceModel));
        modelAndView.setViewName(VIEW_EDIT_SCHEDULE_EVENT);
        return modelAndView;
    }

    @PostMapping("/edit-event/{scheduleID}/{eventID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView editScheduleEventConfirm(@PathVariable("scheduleID") String scheduleID,
                                                 @PathVariable("eventID") String eventID,
                                                 @ModelAttribute EventEditBindingModel eventEditBindingModel,
                                                 ModelAndView modelAndView) {

        EventServiceModel eventServiceModel = this.modelMapper.map(eventEditBindingModel, EventServiceModel.class);
        eventServiceModel.setId(eventID);
        eventServiceModel.setSport(this.sportService.findByName(eventEditBindingModel.getSport()));

        EventServiceModel updatedEventServiceModel = this.eventService.updateEvent(eventServiceModel);
        ScheduleServiceModel scheduleServiceModel = this.scheduleService.findByID(scheduleID);
        this.scheduleService.updateEvent(scheduleServiceModel, updatedEventServiceModel);

        String sportCenterID = scheduleServiceModel.getSportCenter().getId();
        modelAndView.setViewName(REDIRECT_TO_SCHEDULE_DETAILS_BY_ID + scheduleID);
        return modelAndView;
    }

    @PostMapping("/delete-event/{scheduleID}/{eventID}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ModelAndView deleteScheduleEvent(
            @PathVariable("scheduleID") String scheduleID,
            @PathVariable("eventID") String eventID,
            ModelAndView modelAndView) {

        ScheduleServiceModel scheduleServiceModel = this.scheduleService.findByID(scheduleID);
        EventServiceModel eventServiceModel = this.eventService.findByID(eventID);
        this.scheduleService.deleteEvent(scheduleServiceModel, eventServiceModel);

        modelAndView.setViewName(REDIRECT_TO_SCHEDULE_DETAILS_BY_ID + scheduleID);
        return modelAndView;
    }
}
