package com.niit.stackroute.service;

import com.niit.stackroute.domain.UserAuthentication;
import com.niit.stackroute.exception.UserAlreadyExistsException;
import com.niit.stackroute.exception.UserNotFoundException;
import com.niit.stackroute.repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService{

    private UserAuthenticationRepository UserRepository;

    @Autowired
    private UserAuthenticationServiceImpl(UserAuthenticationRepository UserRepository){
        this.UserRepository=UserRepository;
    }


    @Override
    public UserAuthentication saveUser(UserAuthentication user) throws UserAlreadyExistsException {
        if (UserRepository.findById(user.getEmail()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        return UserRepository.save(user);
    }

    @Override
    public UserAuthentication findByUserEmail(String email) throws UserNotFoundException {
        UserAuthentication user = UserRepository.findByEmail(email);
        if(user==null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public List<UserAuthentication> getAllUsers(){
        return UserRepository.findAll();
    }
}
