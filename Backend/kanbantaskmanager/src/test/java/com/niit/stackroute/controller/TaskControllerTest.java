package com.niit.stackroute.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.stackroute.domain.Task;
import com.niit.stackroute.exception.TaskAlreadyExistsException;
import com.niit.stackroute.service.TaskService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;
    private Task task1, task2;
    List<Task> taskList;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    public void setUp()
    {
        task1 = new Task("1","shikhar@gmail.com","todo","high","Backend","Space1","part of Main Project",new Date(2022-04-22),new Date(2022-04-25));
        taskList = Arrays.asList(task1);

        //make mock http requests
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @AfterEach
    public void tearDown()
    {
        task1 = null;
        task2 = null;
    }

    @Test
    public void givenTaskToSaveReturnSaveTaskSuccess() throws Exception
    {
        when(taskService.saveTaskDetails(any())).thenReturn(task1);


        mockMvc.perform(post("/api/v1/task")//making dummy http post request
                        .contentType(MediaType.APPLICATION_JSON)//setting the content type of the post request
                        .content(jsonToString(task1)))//firstly, java object will be converted to json string then will  be passed with the mock http request.
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(taskService,times(1)).saveTaskDetails(any());
    }

    @Test
    public void givenTaskToSaveReturnSaveTaskFailure() throws Exception
    {
        when(taskService.saveTaskDetails(any())).thenThrow(TaskAlreadyExistsException.class);

        mockMvc.perform(post("/api/v1/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(task1)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(taskService,times(1)).saveTaskDetails(any());

    }

    @Test
    public void givenTaskIdDeleteTask() throws Exception
    {
        when(taskService.deleteTask(anyString())).thenReturn(true);

        mockMvc.perform(delete("/api/v1/task/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(taskService,times(1)).deleteTask(anyString());

    }

    private static String jsonToString(final Object ob) throws JsonProcessingException
    {
        String result;

        try
        {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            System.out.println("Json Content that has been posted:\n"+jsonContent);
            result = jsonContent;

        }
        catch(JsonProcessingException e)
        {
            result = "JSON processing error";
        }

        return result;
    }
}
