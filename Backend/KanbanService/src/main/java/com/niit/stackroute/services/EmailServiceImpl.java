package com.niit.stackroute.services;

import com.niit.stackroute.domain.Notification;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Service
public class EmailServiceImpl implements EmailService
{

    private static final Logger logger = LoggerFactory
            .getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendTextEmail(String email,int total)
    {
        logger.info("Simple Email sending start");

        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(email);
        simpleMessage.setSubject("Email Notifications From Kanban App");


        simpleMessage.setText("You have "+total+" No of Tasks Expired. Visit the Kanban board to check the Expired Tasks. THANK YOU ...");


        javaMailSender.send(simpleMessage);

        logger.info("Email sent for "+email);
    }
}
