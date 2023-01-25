package com.niit.stackroute.services;

import com.niit.stackroute.domain.Status;

import java.util.List;
import java.util.Optional;

public interface StatusService
{
    public Status saveStatus(Status status);
    public List<Status> getAllStatus();
    public boolean deleteStatusById(String statusName);
    public List<Status> getByEmailAndSpace(String email,String space);
    public Status getByStatusNameAndEmailAndSpace(String statusName, String email, String spaceName);

    public Optional<Status> getStatusById(String statusId);
}
