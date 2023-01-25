package com.niit.stackroute.service;

import com.niit.stackroute.domain.Notification;

import java.util.List;

public interface NotificationService
{
    public List<Notification> getAllNotifications();
    public Notification getNotificationById(int id);
    public Notification saveNotification(Notification notification);

}
