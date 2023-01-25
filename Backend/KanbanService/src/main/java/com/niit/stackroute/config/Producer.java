package com.niit.stackroute.config;

import com.niit.stackroute.domain.Task;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rabbitmq.domain.NotificationDTO;
import rabbitmq.domain.TaskDTO;

import java.util.List;

@Component
public class Producer
{
    private RabbitTemplate rabbitTemplate;
    private DirectExchange exchange;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, DirectExchange exchange)
    {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    public void sendTaskToRabbitMq(TaskDTO taskDTO)
    {
        System.out.println("-------- taskDto -----------");
        System.out.println(taskDTO);
        rabbitTemplate.convertSendAndReceive(exchange.getName(),"task_queue1",taskDTO);
    }

    public List<TaskDTO> getTasksBySpaceAndEmailAndStatus(String spaceName, String email)
    {
        String[] data ={spaceName,email};
        List<TaskDTO> response = (List<TaskDTO>) rabbitTemplate.convertSendAndReceive(exchange.getName(),"task_queue2",data);
        System.out.println("--------- Received from Consumer -----------");
        System.out.println(response);
        return response;
    }

    public TaskDTO updateTask(Task task,String id)
    {
        TaskDTO updatedTaskDTO = (TaskDTO) rabbitTemplate.convertSendAndReceive(exchange.getName(),"task_queue3",task);

        System.out.println("--------- Received from Consumer -----------");
        System.out.println(updatedTaskDTO);
        return updatedTaskDTO;
    }

    public String deleteTaskById(String id)
    {
        String message = (String) rabbitTemplate.convertSendAndReceive(exchange.getName(),"task_queue4",id);
        return message;
    }

    public List<TaskDTO> getAllTasksByEmail(String email)
    {

        List<TaskDTO> response = (List<TaskDTO>) rabbitTemplate.convertSendAndReceive(exchange.getName(),"task_queue5",email);
        System.out.println("--------- Received from Consumer -----------");
        System.out.println(response);
        return response;
    }


    public NotificationDTO getByIdNotification(Integer id)
    {

        NotificationDTO notificationDTO = (NotificationDTO) rabbitTemplate.convertSendAndReceive(exchange.getName(),"routing_noti_token2",id);
        return notificationDTO;
    }

    public List<NotificationDTO> getAllnotifications()
    {
        System.out.println("inside producer");
        List<NotificationDTO> notificationDTOList = (List<NotificationDTO>) rabbitTemplate.convertSendAndReceive(exchange.getName(),"routing_noti_token","message");
        return notificationDTOList;
    }
}
