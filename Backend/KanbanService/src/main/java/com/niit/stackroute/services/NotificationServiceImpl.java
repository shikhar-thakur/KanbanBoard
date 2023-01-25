package com.niit.stackroute.services;

import com.netflix.discovery.converters.Auto;
import com.niit.stackroute.config.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rabbitmq.domain.NotificationDTO;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{


    private Producer producer;

    @Autowired
    public NotificationServiceImpl(Producer producer) {
        this.producer = producer;
    }

    @Override
    public List<NotificationDTO> getAllNotifications() {
        System.out.println("inside all notification method");
        return producer.getAllnotifications();
    }

    @Override
    public NotificationDTO getNotificationById(Integer Id) {

        return producer.getByIdNotification(Id);
    }
}
