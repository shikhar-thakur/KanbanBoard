package com.niit.stackroute.repository;

import com.niit.stackroute.domain.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends MongoRepository<Status,String> {

    public List<Status> findByStatusName(String statusName);
    public List<Status> findByEmailAndSpaceName(String email,String spaceName);
    public Status findByStatusNameAndEmailAndSpaceName(String statusName, String email, String spaceName);
}
