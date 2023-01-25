package com.niit.stackroute.service;

import com.niit.stackroute.domain.Task;
import com.niit.stackroute.exception.TaskAlreadyExistsException;
import com.niit.stackroute.exception.TaskNotFoundException;
import com.niit.stackroute.repository.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest
{
    //create dummy object for TaskRepository
    @Mock
    private TaskRepository taskRepository;

    //in this object inject the dummy object as external dependency
    @InjectMocks
    private TaskServiceImpl taskService;
    private Task task1, task2;
    List<Task> taskList;


    @BeforeEach
    public void setUp()
    {
        task1 = new Task("1","shikhar@gmail.com","todo","high","Backend","Space1","part of Main Project",new Date(2022-04-22),new Date(2022-04-25));
        taskList = Arrays.asList(task1);
    }

    @AfterEach
    public void tearDown()
    {
        task1 = null;
        task2 = null;
    }

    @Test
    public void givenTaskToSaveAndReturnSavedTaskSuccess() throws TaskAlreadyExistsException
    {
        when(taskRepository.findById(task1.getTaskId())).thenReturn(Optional.ofNullable(null));
        when(taskRepository.save(any())).thenReturn(task1);

        assertEquals(task1,taskService.saveTaskDetails(task1));

        verify(taskRepository,times(1)).save(any());
        verify(taskRepository,times(1)).findById(any());
    }

    @Test
    public void givenProductToSaveReturnProductFailure()
    {
        when(taskRepository.findById(task1.getTaskId())).thenReturn(Optional.ofNullable(task1));
        assertThrows(TaskAlreadyExistsException.class,()->taskService.saveTaskDetails(task1));

        verify(taskRepository,times(0)).save(any());
        verify(taskRepository,times(0)).findAllById(any());
    }

    @Test
    public void givenTaskToDeleteShouldDeleteSuccess() throws TaskNotFoundException
    {
        when(taskRepository.findById(task1.getTaskId())).thenReturn(Optional.ofNullable(task1));
        boolean flag = taskService.deleteTask(task1.getTaskId());
        assertEquals(true,flag);

        verify(taskRepository,times(1)).deleteById(any());
        verify(taskRepository,times(1)).findById(any());

    }

}
