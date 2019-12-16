package com.softuni.sportify.config;

import com.softuni.sportify.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
public class AppScheduleConfig {

    private final EmailService emailService;

    @Autowired
    public AppScheduleConfig(EmailService emailService) {
        this.emailService = emailService;
    }
//
//    @Scheduled(fixedRate = 30000)
//    private void sendEmail() {
//
//        LocalDateTime now = LocalDateTime.now();
//        String address = "sportifyAppDiplomaDefence@gmail.com";
//        String subject = String.format("%s:%s:%s",now.getHour() , now.getMinute(), now.getSecond());
//        String text = now.toString();
//        this.emailService.sendEmail(address, subject, text);
//    }
}
