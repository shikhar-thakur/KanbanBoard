package com.niit.stackroute.repository;

import com.niit.stackroute.domain.Space;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends MongoRepository<Space,String> {
    public List<Space> findByEmail(String email);
    public Space findByEmailAndSpaceName(String email, String spaceName);
}
