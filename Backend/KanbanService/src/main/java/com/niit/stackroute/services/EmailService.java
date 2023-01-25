package com.niit.stackroute.services;

import com.niit.stackroute.domain.Notification;
import org.springframework.stereotype.Service;

@Service
public interface EmailService
{
    void sendTextEmail(String email,int total);
}
