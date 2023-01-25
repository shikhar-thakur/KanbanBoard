package com.niit.stackroute.config;


import com.niit.stackroute.domain.Notification;
import com.niit.stackroute.service.NotificationService;
import com.niit.stackroute.service.NotificationServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rabbitmq.domain.NotificationDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class Consumer {

    @Autowired
    private NotificationServiceImpl notificationService;

    @RabbitListener(queues = "noti_get_queue")
    public List<NotificationDTO> getNotificationDTOFromRabbitMq()
    {
        System.out.println("inside allnoti notification service");
        List<Notification> notificationList= notificationService.getAllNotifications();

        List<NotificationDTO> notificationDTOSList = new ArrayList();

        for(int i=0;i < notificationList.size();i++)
        {
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setNotificationId(notificationList.get(i).getNotificationId());
            notificationDTO.setNotification(notificationList.get(i).getNotification());
            notificationDTOSList.add(notificationDTO);
        }
        return notificationDTOSList;
    }
    @RabbitListener(queues = "noti_getbyid_queue")
    public NotificationDTO getByIdNotificationDTOFromRabbitMq(int id)
    {
        System.out.println("inside getbyid listener");
        Notification notification= notificationService.getNotificationById(id);

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setNotificationId(notification.getNotificationId());
        notificationDTO.setNotification(notification.getNotification());

        return notificationDTO;
    }
}
