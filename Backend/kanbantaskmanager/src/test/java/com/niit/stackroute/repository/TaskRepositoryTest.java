package com.niit.stackroute.repository;

import com.niit.stackroute.domain.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class TaskRepositoryTest
{
    @Autowired
    private TaskRepository taskRepository;
    private Task task;

    @BeforeEach
    public void setUp()
    {
        task = new Task("1","shikhar@gmail.com","todo","high","Backend","Space1","part of Main Project",new Date(2022-04-22),new Date(2022-04-25));
    }

    @AfterEach
    public void tearDown()
    {
        task = null;
        taskRepository.deleteById("1");
    }

    @Test
    public void givenTaskToSaveShouldReturnTask()
    {
        taskRepository.insert(task);
        Task task1 = taskRepository.findById(task.getTaskId()).get();
        //Expecting the Task object should not be null.
        assertNotNull(task1);

        //assertEquals(ExpectedValue,ActualValue)
        assertEquals(task.getTaskId(),task1.getTaskId());
    }

    //@Test
    public void givenTaskToDeleteShouldDeleteTask()
    {
        taskRepository.insert(task);
        Task task1 = taskRepository.findById(task.getTaskId()).get();

        taskRepository.delete(task1);
        assertEquals(Optional.empty(),taskRepository.findById(task.getTaskId()));
    }

    //@Test
    public void givenTaskReturnGetAllTasks()
    {
        taskRepository.insert(task);
        Task task1 = new Task("2","shikhar@gmail.com","todo","high","Backend","Space1","part of Main Project",new Date(2022-04-22),new Date(2022-04-25));
        taskRepository.insert(task1);

        List<Task> list = taskRepository.findAll();
        assertEquals(2,list.size());
        assertEquals("todo",list.get(1).getStatusName());
    }
}
