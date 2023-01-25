package com.niit.stackroute.controller;

import com.niit.stackroute.domain.Notification;
import com.niit.stackroute.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class NotificationController
{
    private ResponseEntity responseEntity;
    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService)
    {
        this.notificationService = notificationService;
    }

    @GetMapping("notifications")
    public ResponseEntity<?> getAllNotifications()
    {
       responseEntity =  new ResponseEntity<>(notificationService.getAllNotifications(), HttpStatus.OK);
       return responseEntity;
    }

    @GetMapping("notification/{notificationId}")
    public ResponseEntity<?> getNotificationById(@PathVariable int notificationId)
    {
        responseEntity = new ResponseEntity<>(notificationService.getNotificationById(notificationId),HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("notification")
    public ResponseEntity<?> saveNotification(@RequestBody Notification notification)
    {
        return new ResponseEntity<>(notificationService.saveNotification(notification),HttpStatus.CREATED);
    }
}
