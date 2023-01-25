package com.niit.stackroute.controller;

import com.niit.stackroute.domain.Task;
import com.niit.stackroute.exception.TaskAlreadyExistsException;
import com.niit.stackroute.exception.TaskNotFoundException;
import com.niit.stackroute.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class TaskController
{
    private ResponseEntity responseEntity;
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService)
    {
        this.taskService = taskService;
    }

    @PostMapping("task")
    public ResponseEntity<?> saveTask(@RequestBody Task task) throws TaskAlreadyExistsException
    {
        try
        {
            responseEntity = new ResponseEntity(taskService.saveTaskDetails(task),HttpStatus.CREATED);
        }
        catch (TaskAlreadyExistsException e)
        {
            throw new TaskAlreadyExistsException();
        }
        catch (Exception e)
        {
            responseEntity = new ResponseEntity<>("Error  !!!Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("tasks")
    public ResponseEntity<?> getTasks()
    {
        return new ResponseEntity<>(taskService.getAllTasks(),HttpStatus.OK);
    }

    @PutMapping("/task/{taskId}")
    public ResponseEntity<?> updateTaskById(@RequestBody Task task ,@PathVariable String taskId)
    {
        return new ResponseEntity<>(taskService.updateTask(task,taskId), HttpStatus.OK);
    }

    @DeleteMapping("/task/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable String taskId) throws TaskNotFoundException
    {
        try
        {
            taskService.deleteTask(taskId);
            responseEntity = new ResponseEntity("Successfully deleted !!!", HttpStatus.OK);
        }
        catch (TaskNotFoundException e)
        {
            throw new TaskNotFoundException();
        }
        catch (Exception exception)
        {
            responseEntity = new ResponseEntity("Error !!! Try after sometime.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @GetMapping("/task/{spaceName}/{email}")
    public ResponseEntity<?> getBySpaceAndStatusAndEmail(@PathVariable String spaceName,@PathVariable String email)
    {
        responseEntity = new ResponseEntity(taskService.getTaskByStatusAndEmailAndSpace(spaceName,email),HttpStatus.OK);
        return responseEntity;
    }

}
