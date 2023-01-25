package com.niit.stackroute.config;

import com.niit.stackroute.domain.Task;
import com.niit.stackroute.exception.TaskAlreadyExistsException;
import com.niit.stackroute.exception.TaskNotFoundException;
import com.niit.stackroute.service.TaskServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rabbitmq.domain.TaskDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Consumer {
    @Autowired
    private TaskServiceImpl taskService;

    @RabbitListener(queues = "task_queue1")
    public void saveTask(TaskDTO taskDTO) throws TaskAlreadyExistsException {

        Task task = new Task();
        task.setTaskId(taskDTO.getTaskId());
        task.setEmail(taskDTO.getEmail());
        task.setStatusName(taskDTO.getStatusName());
        task.setTaskName(taskDTO.getTaskName());
        task.setSpaceName(taskDTO.getSpaceName());
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setStartDate(taskDTO.getStartDate());
        task.setEndDate(taskDTO.getEndDate());
        task.setPriority(taskDTO.getPriority());

        taskService.saveTaskDetails(task);

    }

    @RabbitListener(queues = "task_queue2")
    public List<TaskDTO> getAllTasksByStatusAndEmailAndSpace(String[] taskData)
    {
        System.out.println(taskData[0]);
        System.out.println(taskData[1]);


        List<Task> taskList = taskService.getTaskByStatusAndEmailAndSpace(taskData[0],taskData[1]);
        System.out.println("---------------taskList from mongo---------");
        System.out.println(taskList);

        List<TaskDTO> tasks = new ArrayList<TaskDTO>();

        taskList.forEach(n -> {
            TaskDTO task = new TaskDTO();
            task.setTaskId(n.getTaskId());
            task.setEmail(n.getEmail());
            task.setStatusName(n.getStatusName());
            task.setTaskName(n.getTaskName());
            task.setSpaceName(n.getSpaceName());
            task.setTaskDescription(n.getTaskDescription());
            task.setStartDate(n.getStartDate());
            task.setEndDate(n.getEndDate());
            task.setPriority(n.getPriority());
            System.out.println("---task inside foreach------");
            System.out.println(task);
            tasks.add(task);

            System.out.println("--- tasks inside foreach-------");
            System.out.println(tasks);

        });

        System.out.println("---------- Return to Producer ----------");
        System.out.println(tasks);

        return tasks;
    }

    @RabbitListener(queues = "task_queue3")
    public TaskDTO updateTaskById(Task task)
    {
        Task updatedTask = taskService.updateTask(task,task.getTaskId());

        TaskDTO taskDto = new TaskDTO();
        taskDto.setTaskId(updatedTask.getTaskId());
        taskDto.setEmail(updatedTask.getEmail());
        taskDto.setStatusName(updatedTask.getStatusName());
        taskDto.setTaskName(updatedTask.getTaskName());
        taskDto.setSpaceName(updatedTask.getSpaceName());
        taskDto.setTaskDescription(updatedTask.getTaskDescription());
        taskDto.setStartDate(updatedTask.getStartDate());
        taskDto.setEndDate(updatedTask.getEndDate());
        taskDto.setPriority(updatedTask.getPriority());

        return taskDto;
    }

    @RabbitListener(queues = "task_queue4")
    public String deleteTaskById(String id) throws TaskNotFoundException
    {
        taskService.deleteTask(id);
        List<Task> taskList = taskService.getAllTasks();

        int count = 0;
        for (int i = 0; i < taskList.size(); i++)
        {
            if (taskList.get(i).getTaskId() == id)
            {
                count++;
            }
        }

        String message = null;
        
        if (count == 0)
        {
            message =  "Task Deleted Successfully";
        }

        return message;
    }

    @RabbitListener(queues = "task_queue5")
    public List<TaskDTO> sendAllTasksByEmail(String email)
    {

        List<Task> taskList = taskService.getTasksByEmail(email);
        System.out.println("---------------taskList from mongo---------");
        System.out.println(taskList);

        List<TaskDTO> tasks = new ArrayList<TaskDTO>();

        taskList.forEach(n -> {
            TaskDTO task = new TaskDTO();
            task.setTaskId(n.getTaskId());
            task.setEmail(n.getEmail());
            task.setStatusName(n.getStatusName());
            task.setTaskName(n.getTaskName());
            task.setSpaceName(n.getSpaceName());
            task.setTaskDescription(n.getTaskDescription());
            task.setStartDate(n.getStartDate());
            task.setEndDate(n.getEndDate());
            task.setPriority(n.getPriority());
            System.out.println("---task inside foreach------");
            System.out.println(task);
            tasks.add(task);

            System.out.println("--- tasks inside foreach-------");
            System.out.println(tasks);

        });

        System.out.println("---------- Return to Producer ----------");
        System.out.println(tasks);

        return tasks;
    }
}
