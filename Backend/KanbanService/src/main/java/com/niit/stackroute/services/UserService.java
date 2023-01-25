package com.niit.stackroute.services;

import com.niit.stackroute.domain.User;
import com.niit.stackroute.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User saveUser(User user);
//    public User getUserById(User user) throws UserNotFoundException;


   public Object getUserByEmail(User user) throws UserNotFoundException;

   public User getUser(String email);
}
