package com.niit.stackroute.services;

import com.niit.stackroute.domain.User;
import com.niit.stackroute.exceptions.UserNotFoundException;
import com.niit.stackroute.proxy.UserProxy;
import com.niit.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserProxy userProxy;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }

    @Override
    public User saveUser(User user) {
        System.out.println(user);
        System.out.println("line 26");

        ResponseEntity responseEntity = userProxy.registeringUser(user);
        System.out.println(responseEntity.getBody());
        return this.userRepository.save(user);
    }

    @Override
    public Object getUserByEmail(User user) throws UserNotFoundException {
        ResponseEntity responseEntity = userProxy.generatingToken(user);
        System.out.println("inside service");
        System.out.println(responseEntity.getBody());
        User user1 = this.userRepository.findByEmail(user.getEmail());
        if (user.getEmail().equals(user1.getEmail()) && user.getPassword().equals(user1.getPassword()))
            return responseEntity.getBody();
        else
            return null;
    }

    @Override
    public User getUser(String email)
    {
        return userRepository.findByEmail(email);
    }

}
