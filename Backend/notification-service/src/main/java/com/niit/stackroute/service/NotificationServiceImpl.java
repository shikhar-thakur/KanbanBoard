package com.niit.stackroute.service;

import com.niit.stackroute.domain.Notification;
import com.niit.stackroute.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService
{
    private NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository)
    {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getAllNotifications()
    {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getNotificationById(int id)
    {
        return notificationRepository.findByNotificationId(id);
    }

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}
