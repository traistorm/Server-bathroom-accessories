package com.example.server.Service;

import com.example.server.Entity.CoatHanger;
import com.example.server.Repository.CoatHangerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoatHangerService {

    @Autowired
    CoatHangerRepository coatHangerRepository;
    public List<CoatHanger> findAll()
    {
        return coatHangerRepository.findAll();
    }
    public CoatHanger findCoatHangerById(Integer id)
    {
        return coatHangerRepository.findCoatHangerById(id);
    }
}
