package com.niit.stackroute.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Notification
{
    @Id
    private int notificationId;
    private String notification;


    public Notification()
    {

    }

    public Notification(int notificationId, String notification)
    {
        this.notificationId = notificationId;
        this.notification = notification;

    }

    public int getNotificationId()
    {
        return notificationId;
    }
    public void setNotificationId(int notificationId)
    {
        this.notificationId = notificationId;
    }

    public String getNotification()
    {
        return notification;
    }
    public void setNotification(String notification)
    {
        this.notification = notification;
    }

    @Override
    public String toString()
    {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", notification='" + notification + '\'' +
                '}';
    }
}
