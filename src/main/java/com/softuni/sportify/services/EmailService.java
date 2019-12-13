package com.softuni.sportify.services;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendEmail(String address, String subject, String text);
}
