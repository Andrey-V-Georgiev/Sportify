package com.softuni.sportify.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSenderImpl javaMailSenderImpl;

    @Autowired
    public EmailServiceImpl(JavaMailSenderImpl javaMailSenderImpl) {
        this.javaMailSenderImpl = javaMailSenderImpl;
    }

    @Override
    public void sendEmail(String address, String subject, String text) {

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(address);
        email.setSubject(subject);
        email.setText(text);
        javaMailSenderImpl.send(email);
    }
}
