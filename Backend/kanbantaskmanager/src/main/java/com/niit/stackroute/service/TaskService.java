package com.niit.stackroute.service;

import com.niit.stackroute.domain.Task;
import com.niit.stackroute.exception.TaskAlreadyExistsException;
import com.niit.stackroute.exception.TaskNotFoundException;

import java.util.List;

public interface TaskService
{
    Task saveTaskDetails(Task task) throws TaskAlreadyExistsException;

    boolean deleteTask(String id) throws TaskNotFoundException;

    public List<Task> getAllTasks();

    Task updateTask(Task task, String id);
    public List<Task> getTaskByStatusAndEmailAndSpace(String spaceName,String email);
    public List<Task> getTaskByStatusAndEmailAndSpaceAndTaskId(String spaceName,String email, String statusName,String taskId);
    public List<Task> getTasksByEmail(String emailId);

}
