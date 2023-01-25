package com.niit.stackroute.repository;

import com.niit.stackroute.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task,String>
{
    public List<Task> findBySpaceNameAndEmail(String spaceName, String email);

    public List<Task> findBySpaceNameAndEmailAndStatusNameAndTaskId(String spaceName, String email, String statusName,String taskId);

    public List<Task> findByEmail(String email);
}
