package com.niit.stackroute.services;

import com.niit.stackroute.config.Producer;
import com.niit.stackroute.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rabbitmq.domain.TaskDTO;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService
{
    private Producer producer;

    @Autowired
    public TaskServiceImpl(Producer producer)
    {
        this.producer = producer;
    }

    @Override
    public Task saveTaskDetails(Task task)
    {
        TaskDTO tasks = new TaskDTO();
        tasks.setTaskId(task.getTaskId());
        tasks.setEmail(task.getEmail());
        tasks.setStatusName(task.getStatusName());
        tasks.setTaskName(task.getTaskName());
        tasks.setSpaceName(task.getSpaceName());
        tasks.setTaskDescription(task.getTaskDescription());
        tasks.setStartDate(task.getStartDate());
        tasks.setEndDate(task.getEndDate());
        tasks.setPriority(task.getPriority());

        System.out.println("-----task------");
        System.out.println(task);

        System.out.println("\n-------tasks-----------");
        System.out.println(tasks);
        producer.sendTaskToRabbitMq(tasks);
        return task;
    }

//    @Override
//    public List<TaskDTO> getAllTasks()
//    {
//        return producer.getAllTasksFromConsumer();
//    }

    @Override
    public TaskDTO updateTaskBYId(Task task, String id)
    {
        return producer.updateTask(task,id);
    }

    @Override
    public String deleteTask(String id)
    {
        return producer.deleteTaskById(id);
    }

    @Override
    public List<TaskDTO> getAllTaskByStatusAndEmailAndSpace(String spaceName, String email)
    {
        return producer.getTasksBySpaceAndEmailAndStatus(spaceName,email);
    }

    @Override
    public List<TaskDTO> getTaskByEmail(String email)
    {
        return producer.getAllTasksByEmail(email);
    }

}
