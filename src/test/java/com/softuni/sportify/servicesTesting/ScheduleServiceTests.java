//package com.softuni.sportify.servicesTesting;
//
//
//import com.softuni.sportify.domain.models.service_models.AddressServiceModel;
//import com.softuni.sportify.domain.models.service_models.ImageServiceModel;
//import com.softuni.sportify.domain.models.service_models.SportCenterServiceModel;
//import com.softuni.sportify.exceptions.CreateException;
//import com.softuni.sportify.repositories.ScheduleRepository;
//import com.softuni.sportify.repositories.SportCenterRepository;
//import com.softuni.sportify.services.EventService;
//import com.softuni.sportify.services.ScheduleService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class ScheduleServiceTests {
//
//    @Autowired
//    private ScheduleService scheduleService;
//
//    @MockBean
//    private ScheduleRepository mockScheduleRepository;
//
//    @MockBean
//    private SportCenterRepository mockSportCenterRepository;
//
//    @MockBean
//    private EventService mockEventService;
//
//    @Test
//    public void createSchedule_dateIsValid_throwsCreateException() throws CreateException {
//
//        SportCenterServiceModel sportCenterServiceModel = new SportCenterServiceModel();
//        sportCenterServiceModel.setName("tgerg");
//        sportCenterServiceModel.setAddress(new AddressServiceModel());
//        sportCenterServiceModel.setIconImage(new ImageServiceModel());
//        sportCenterServiceModel.setSports(new ArrayList<>());
//        sportCenterServiceModel.setCalendar(new ArrayList<>());
//        sportCenterServiceModel.setSportCenterImages(new ArrayList<>());
//        sportCenterServiceModel.setDescription("tawgtawtqwtg");
//
//        scheduleService.createSchedule(sportCenterServiceModel, "10", "10", "2020");
//        verify(mockScheduleRepository).saveAndFlush(any());
//    }
//
//    @Test(expected = CreateException.class)
//    public void createSchedule_dateOverBorders_throwsCreateException() throws CreateException {
//
//        SportCenterServiceModel sportCenterServiceModel = new SportCenterServiceModel();
//        scheduleService.createSchedule(sportCenterServiceModel, "32", "13", "2051");
//    }
//
//    @Test(expected = CreateException.class)
//    public void createSchedule_dateUnderBorders_throwsCreateException() throws CreateException {
//
//        SportCenterServiceModel sportCenterServiceModel = new SportCenterServiceModel();
//        scheduleService.createSchedule(sportCenterServiceModel, "0", "0", "1989");
//    }
//}
