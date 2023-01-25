package com.niit.stackroute.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.niit.stackroute.domain.User;
import com.niit.stackroute.exceptions.UserNotFoundException;
import com.niit.stackroute.services.EmailService;
import com.niit.stackroute.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@RestController
@CrossOrigin
public class UserController
{
    private UserService userService;
    private ResponseEntity responseEntity;


    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;

    }

    @PostMapping("/register")
    @HystrixCommand(fallbackMethod = "fallbackuserregister",commandKey = "registerkey", groupKey = "userregister")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws InterruptedException {
        //Thread.sleep(Long.parseLong("5000"));
//        Thread.sleep(500L);
        System.out.println("abababaabab");
        responseEntity = new ResponseEntity(this.userService.saveUser(user), HttpStatus.CREATED);
        return responseEntity;
    }

    public ResponseEntity<?>fallbackuserregister(@RequestBody User user) throws Exception{
        String msg = "Service Under Maintenence";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    @HystrixCommand(fallbackMethod = "fallbackuserlogin",commandKey = "loginkey", groupKey = "userlogin")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    public ResponseEntity<?> login(@RequestBody User user) throws UserNotFoundException, InterruptedException {
//        Thread.sleep(5000L);
        try{

//            if(userObj.getEmail().equals(user.getEmail()) && userObj.getPassword().equals(user.getPassword()))
            {
                System.out.println("inside controllr if");
                if(this.userService.getUserByEmail(user).equals(null))
                {
                    return new ResponseEntity("Invalid Credentials",HttpStatus.CONFLICT);
                }
                else
                return new ResponseEntity(this.userService.getUserByEmail(user),HttpStatus.OK);
            }

        }catch (UserNotFoundException unf)
        {
            throw new UserNotFoundException();
        }
    }
    public ResponseEntity<?> fallbackuserlogin(@RequestBody User user) throws Exception{
//        String msg = "Invalid Credential";
//        if(userService.getUserByEmail(user).getEmail()!=null)
            String msg = "Service Under Maintenence";
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email)
    {
        return new ResponseEntity<>(userService.getUser(email),HttpStatus.OK);
    }




}
