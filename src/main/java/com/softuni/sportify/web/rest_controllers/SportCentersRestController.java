package com.softuni.sportify.web.rest_controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softuni.sportify.domain.models.service_models.ScheduleServiceModel;
import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.services.SportCenterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/sport-centers")
public class SportCentersRestController {

    private final ObjectMapper objectMapper;
    private final SportCenterService sportCenterService;

    @Autowired
    public SportCentersRestController(ObjectMapper objectMapper,
                                      SportCenterService sportCenterService) {
        this.objectMapper = objectMapper;
        this.sportCenterService = sportCenterService;
    }

    @GetMapping("/schedules-by-month/{scID}/{monthNum}")
    public String schedulesByMonth(@PathVariable("scID") String scID,
                                   @PathVariable("monthNum") String monthNum) throws JsonProcessingException, ReadException {

        SportCenterServiceModel sportCenterServiceModel = this.sportCenterService.findByID(scID);
        List<ScheduleServiceModel> scheduleObjects = sportCenterServiceModel
                .getCalendar()
                .stream()
                .filter(s -> s.getMonth() == Integer.parseInt(monthNum))
                .collect(Collectors.toList());
        List<Integer> scheduleDays = scheduleObjects
                .stream()
                .map(s -> s.getDay())
                .collect(Collectors.toList());

        return objectMapper.writeValueAsString(scheduleDays);
    }

}
