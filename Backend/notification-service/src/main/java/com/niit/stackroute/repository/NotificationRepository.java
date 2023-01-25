package com.niit.stackroute.repository;

import com.niit.stackroute.domain.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, Integer>
{
    public Notification findByNotificationId(int id);
}
