package com.niit.stackroute.services;

import com.niit.stackroute.domain.Task;
import rabbitmq.domain.TaskDTO;

import java.util.List;

public interface TaskService
{
    public Task saveTaskDetails(Task task);
    //public List<TaskDTO> getAllTasks();
    public TaskDTO updateTaskBYId(Task task, String id);
    public String deleteTask(String id);
    public List<TaskDTO> getAllTaskByStatusAndEmailAndSpace(String spaceName, String email);
    public List<TaskDTO> getTaskByEmail(String email);
}
