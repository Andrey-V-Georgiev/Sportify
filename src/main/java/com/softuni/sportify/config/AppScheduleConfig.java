package com.softuni.sportify.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
public class AppScheduleConfig {
//
//    private final AdminService adminService;
//
//    @Autowired
//    public AppScheduleConfig(AdminService adminService) {
//        this.adminService = adminService;
//    }
//
//    @Scheduled(fixedRate = 5000)
//    private void currentThemeScheduler() {
//    Random r = new Random();
//    int randomInt = r.nextInt(adminService.themesSize());
//       this.adminService.setCounter(randomInt);
//     System.out.println("Works!" + randomInt);
//    }
}
