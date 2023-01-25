package com.niit.stackroute.services;

import rabbitmq.domain.NotificationDTO;

import java.util.List;

public interface NotificationService {
        public List<NotificationDTO> getAllNotifications();

        public NotificationDTO getNotificationById(Integer Id);
}
