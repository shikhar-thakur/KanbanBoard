package com.niit.stackroute.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.niit.stackroute.domain.Notification;
import com.niit.stackroute.domain.Space;
import com.niit.stackroute.domain.Status;
import com.niit.stackroute.domain.Task;
import com.niit.stackroute.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@RestController
@CrossOrigin
@RequestMapping("/api/s1/")
public class StatusController {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    @Autowired
    private RestTemplate restTemplate;

    private StatusService statusService;
    private ResponseEntity responseEntity;
    private TaskService taskService;
    private NotificationService notificationService;
    private SpaceService spaceService;
    private EmailService emailService;

    @Autowired
    public StatusController(StatusService statusService, TaskService taskService, NotificationService notificationService, SpaceService spaceService, EmailService emailService) {
        this.statusService = statusService;
        this.taskService = taskService;
        this.notificationService = notificationService;
        this.spaceService = spaceService;
        this.emailService = emailService;
    }

    //space CRUD operations
    @PostMapping("space")
    @HystrixCommand(fallbackMethod = "saveSpaceFallback", commandKey = "spaceKey", groupKey = "saveSpacekey")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    public ResponseEntity<?> saveSpace(@RequestBody Space space) throws InterruptedException {
//        Thread.sleep(500L);
        responseEntity = new ResponseEntity(spaceService.saveSpace(space), HttpStatus.CREATED);
        return responseEntity;
    }

    public ResponseEntity<?> saveSpaceFallback(@RequestBody Space space) {
        String msg = "Service Under Maintenence";
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }


    @GetMapping("spaces")
    public ResponseEntity<?> getAllSpaces() {
        responseEntity = new ResponseEntity(spaceService.getSpace(), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("space/{email}/{spaceName}")
    public ResponseEntity<?> deleteSpaceById(@PathVariable String email, @PathVariable String spaceName) {
        responseEntity = new ResponseEntity(spaceService.deleteSpace(email, spaceName), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("spaces/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        return new ResponseEntity(spaceService.getByEmail(email), HttpStatus.OK);
    }

    //Status CRUD operations
    @PostMapping("status")
    @HystrixCommand(fallbackMethod = "saveStatusFallBack", commandKey = "statusKey", groupKey = "saveStatuskey")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    public ResponseEntity<?> saveStatus(@RequestBody Status status) throws InterruptedException {
        Thread.sleep(500L);
        responseEntity = new ResponseEntity(statusService.saveStatus(status), HttpStatus.CREATED);
        return responseEntity;
    }

    public ResponseEntity<?> saveStatusFallBack(@RequestBody Status status) {
        String msg = "Service Under Maintenence";
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }


    @GetMapping("status/{email}/{spaceName}")
    public ResponseEntity<?> getByEmailAndSpace(@PathVariable String email, @PathVariable String spaceName) {
        responseEntity = new ResponseEntity(statusService.getByEmailAndSpace(email, spaceName), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("status/{statusId}")
    public ResponseEntity<?> deleteStatus(@PathVariable String statusId) {
        ResponseEntity responseEntity = new ResponseEntity(statusService.deleteStatusById(statusId), HttpStatus.OK);
        if (statusService.getStatusById(statusId).isPresent()) {
            return new ResponseEntity<>("Can't Delete The Status", HttpStatus.CONFLICT);
        } else
            return responseEntity;
    }


    //Calling the task manager from kanban service using restTemplate Exchange method
    @PostMapping("task")
//    @HystrixCommand(fallbackMethod = "saveTaskFallBack", commandKey = "taskKey", groupKey = "saveTaskkey")
//    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    public ResponseEntity<?> saveTask(@RequestBody Task task) throws InterruptedException {
//        Thread.sleep(500L);
        System.out.println("---------RequestBody----------");
        System.out.println(task);
        return new ResponseEntity(taskService.saveTaskDetails(task), HttpStatus.CREATED);
    }
//    public ResponseEntity <?>saveTaskFallBack(@RequestBody Task task){
//        String msg = "Service Under Maintenence";
//        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
//    }


//    @GetMapping("tasks")
//    public ResponseEntity<?> getAllTasks()
//    {
//        return new ResponseEntity<>(taskService.getAllTasks(),HttpStatus.OK);
//    }

    @GetMapping("/task/{email}/{spaceName}")
    public ResponseEntity<?> getTaskByStatusName(@PathVariable String email, @PathVariable String spaceName) {
        System.out.println(email + "----" + spaceName + "------");
        return new ResponseEntity<>(taskService.getAllTaskByStatusAndEmailAndSpace(spaceName, email), HttpStatus.OK);
    }

    @PutMapping("/task/{taskId}")
    public ResponseEntity<?> updateProductById(@RequestBody Task task, @PathVariable String taskId) {
        return new ResponseEntity<>(taskService.updateTaskBYId(task, taskId), HttpStatus.OK);
    }

    @DeleteMapping("/task/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable String taskId) {
        return new ResponseEntity<>(taskService.deleteTask(taskId), HttpStatus.OK);
    }

    @GetMapping("task/{email}")
    public ResponseEntity<?> getTaskByEmail(@PathVariable String email) {
        return new ResponseEntity<>(taskService.getTaskByEmail(email), HttpStatus.OK);
    }

//notifications CRUD operations

    @GetMapping("/notifications/{id}")
    public ResponseEntity<?> getNotificationById(@PathVariable Integer id) {
        return new ResponseEntity(notificationService.getNotificationById(id), HttpStatus.OK);
    }

    @GetMapping("/notifications")
    public ResponseEntity<?> getAllNotification() {
        return new ResponseEntity<>(notificationService.getAllNotifications(), HttpStatus.OK);
    }


    @PostMapping("/sendEmail/{email}")
    public ResponseEntity<?> sendEmailToUser(@PathVariable String email, @RequestBody int count)
    {
        System.out.println("--------------- notifications -------------");
        System.out.println(count);
        System.out.println(email);
        emailService.sendTextEmail(email,count);
        return new ResponseEntity("mail sent", HttpStatus.OK);
    }

}