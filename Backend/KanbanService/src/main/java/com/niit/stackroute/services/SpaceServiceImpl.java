package com.niit.stackroute.services;

import com.netflix.discovery.converters.Auto;
import com.niit.stackroute.domain.Space;
import com.niit.stackroute.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceServiceImpl implements SpaceService{
    private SpaceRepository spaceRepository;

    @Autowired
    public SpaceServiceImpl(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    @Override
    public Space saveSpace(Space space) {
        if(spaceRepository.findByEmailAndSpaceName(space.getEmail(),space.getSpaceName())!=null)
        {
            return null;
        }
        return spaceRepository.save(space);
    }

    @Override
    public List<Space> getSpace() {
        return spaceRepository.findAll();
    }

    @Override
    public boolean deleteSpace(String email, String spaceName) {
        boolean flag=false;
        Space space = spaceRepository.findByEmailAndSpaceName(email,spaceName);
        System.out.println(space);
        spaceRepository.delete(space);
        if(spaceRepository.findByEmailAndSpaceName(email,spaceName)!=null)
        {
            flag = true;
        }
    return flag;
    }

    @Override
    public List<Space> getByEmail(String email) {
        return spaceRepository.findByEmail(email);
    }
}
