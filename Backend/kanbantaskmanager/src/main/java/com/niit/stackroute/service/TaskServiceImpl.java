package com.niit.stackroute.service;

import com.niit.stackroute.domain.Task;
import com.niit.stackroute.exception.TaskAlreadyExistsException;
import com.niit.stackroute.exception.TaskNotFoundException;
import com.niit.stackroute.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService
{
    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository)
    {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task saveTaskDetails(Task task) throws TaskAlreadyExistsException
    {
        if(taskRepository.findById(task.getTaskId()).isPresent())
        {
            throw new TaskAlreadyExistsException();
        }
        return taskRepository.save(task);
    }

    @Override
    public boolean deleteTask(String id) throws TaskNotFoundException
    {
        boolean flag = false;
        if(taskRepository.findById(id).isEmpty())
        {
            throw new TaskNotFoundException();
        }
        else
        {
            taskRepository.deleteById(id);
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Task> getAllTasks()
    {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Task task, String id)
    {
        if(taskRepository.findById(id).isEmpty())
        {
            return null;
        }
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTaskByStatusAndEmailAndSpace(String spaceName, String email)
    {
        return taskRepository.findBySpaceNameAndEmail(spaceName,email);
    }

    @Override
    public List<Task> getTaskByStatusAndEmailAndSpaceAndTaskId(String spaceName, String email, String statusName,String taskId)
    {
        return taskRepository.findBySpaceNameAndEmailAndStatusNameAndTaskId(spaceName,email,statusName,taskId);
    }

    @Override
    public List<Task> getTasksByEmail(String emailId)
    {
        return taskRepository.findByEmail(emailId);
    }
}
