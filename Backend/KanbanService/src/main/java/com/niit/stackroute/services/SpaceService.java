package com.niit.stackroute.services;

import com.niit.stackroute.domain.Space;

import java.util.List;

public interface SpaceService {
    public Space saveSpace(Space space);
    public List<Space> getSpace();
    public boolean deleteSpace(String email, String spaceName);
    public List<Space> getByEmail(String email);
}
