package com.niit.stackroute.services;

import com.niit.stackroute.domain.Status;
import com.niit.stackroute.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService
{

    private StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status saveStatus(Status status) {
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbb");
        System.out.println(status);
        return statusRepository.save(status);
    }

    @Override
    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    @Override
    public boolean deleteStatusById(String statusId) {

        statusRepository.deleteById(statusId);
        return true;
    }

    @Override
    public List<Status> getByEmailAndSpace(String email, String space) {
        return statusRepository.findByEmailAndSpaceName(email,space);
    }

    @Override
    public Status getByStatusNameAndEmailAndSpace(String statusName, String email, String spaceName) {

        return statusRepository.findByStatusNameAndEmailAndSpaceName(statusName,email,spaceName);
    }

    @Override
    public Optional<Status> getStatusById(String statusId) {
        return statusRepository.findById(statusId);
    }
}
