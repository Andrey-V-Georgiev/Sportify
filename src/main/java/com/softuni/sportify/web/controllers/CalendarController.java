package com.softuni.sportify.web.controllers;

import com.softuni.sportify.domain.models.binding_models.EventCreateBindingModel;
import com.softuni.sportify.domain.models.binding_models.ScheduleEditBindingModel;
import com.softuni.sportify.domain.models.service_models.EventServiceModel;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import com.softuni.sportify.domain.models.service_models.SportServiceModel;
import com.softuni.sportify.services.EventService;
import com.softuni.sportify.services.ScheduleService;
import com.softuni.sportify.services.SportCenterService;
import com.softuni.sportify.services.SportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.softuni.sportify.constants.CalendarControllerConstants.*;
import static com.softuni.sportify.constants.EventHoursConstants.*;
import static com.softuni.sportify.constants.EventLevelConstants.*;
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
    public ModelAndView showCalendar(@PathVariable("id") String sportCenterID,
                                     ModelAndView modelAndView) {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);
        modelAndView.addObject("sportCenterServiceModel", sportCenterServiceModel);
        modelAndView.setViewName(VIEW_CALENDAR);
        return modelAndView;
    }

    @GetMapping("/create-schedule/{scID}/{day}/{month}/{year}")
    public ModelAndView createSchedule(@PathVariable("scID") String sportCenterID,
                                       @PathVariable("day") String day,
                                       @PathVariable("month") String month,
                                       @PathVariable("year") String year,
                                       ModelAndView modelAndView) {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);
        ScheduleServiceModel scheduleServiceModel = this.scheduleService
                .createSchedule(sportCenterServiceModel, day, month, year);
        String scheduleID = scheduleServiceModel.getId();

        modelAndView.setViewName(REDIRECT_EDIT_SCHEDULE_BY_ID + sportCenterID + "/" + scheduleID);
        return modelAndView;
    }

    @GetMapping("/edit-schedule-by-id/{sportCenterID}/{scheduleID}")
    public ModelAndView editScheduleByID(@PathVariable("sportCenterID") String sportCenterID,
                                         @PathVariable("scheduleID") String scheduleID,
                                         ModelAndView modelAndView) {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);
        ScheduleServiceModel scheduleServiceModel = this.scheduleService.findByID(scheduleID);
        ScheduleEditBindingModel scheduleEditBindingModel = this.modelMapper.map(scheduleServiceModel,
                ScheduleEditBindingModel.class);

        modelAndView.addObject("scheduleEditBindingModel", scheduleEditBindingModel);
        modelAndView.addObject("EVENT_HOURS", EVENT_HOURS);
        modelAndView.addObject("month", MONTH_STRINGS.get(scheduleEditBindingModel.getMonth() - 1));
        modelAndView.setViewName(VIEW_SCHEDULE_DETAILS);
        return modelAndView;
    }

    @GetMapping("/edit-schedule-by-details/{scID}/{day}/{month}/{year}")
    public ModelAndView editScheduleByDetails(@PathVariable("scID") String sportCenterID,
                                              @PathVariable("day") String day,
                                              @PathVariable("month") String month,
                                              @PathVariable("year") String year,
                                              ModelAndView modelAndView) {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(sportCenterID);
        ScheduleServiceModel scheduleServiceModel = this.scheduleService.findByDetails(sportCenterID, day, month, year);
        ScheduleEditBindingModel scheduleEditBindingModel = this.modelMapper.map(scheduleServiceModel,
                ScheduleEditBindingModel.class);

        modelAndView.addObject("scheduleEditBindingModel", scheduleEditBindingModel);
        modelAndView.addObject("EVENT_HOURS", EVENT_HOURS);
        modelAndView.addObject("month", MONTH_STRINGS.get(scheduleEditBindingModel.getMonth() - 1));
        modelAndView.setViewName(VIEW_SCHEDULE_DETAILS);
        return modelAndView;
    }

    @GetMapping("/create-event/{scheduleID}/{hourStr}")
    public ModelAndView createEvent(@PathVariable("scheduleID") String scheduleID,
                                    @PathVariable("hourStr") String hourStr,
                                    ModelAndView modelAndView) {

        modelAndView.addObject("scheduleID", scheduleID);
        modelAndView.addObject("hourStr", hourStr);
        modelAndView.addObject("sportsNames", this.sportService.findAllSportsNames());
        modelAndView.addObject("EVENT_LEVELS", EVENT_LEVELS);

        modelAndView.setViewName(VIEW_CREATE_EVENT);
        return modelAndView;
    }

    @PostMapping("/create-event/{scheduleID}")
    public ModelAndView createEventConfirm(@PathVariable("scheduleID") String scheduleID,
                                           @ModelAttribute EventCreateBindingModel eventCreateBindingModel,
                                           ModelAndView modelAndView) {

        ScheduleServiceModel scheduleServiceModel = this.scheduleService.findByID(scheduleID);
        EventServiceModel eventServiceModel = this.modelMapper.map(eventCreateBindingModel, EventServiceModel.class);
        eventServiceModel.setSport(this.sportService.findByName(eventCreateBindingModel.getSport()));
        EventServiceModel savedEventServiceModel = this.eventService
                                                       .createEvent(eventServiceModel, scheduleServiceModel);
        this.scheduleService.addEvent(scheduleServiceModel, savedEventServiceModel);

        String sportCenterID = scheduleServiceModel.getSportCenter().getId();
        modelAndView.setViewName(REDIRECT_EDIT_SCHEDULE_BY_ID + sportCenterID + "/" + scheduleID);
        return modelAndView;
    }
}
