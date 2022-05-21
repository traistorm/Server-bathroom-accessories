package com.example.server.Service;

import com.example.server.Entity.BathroomAccessories;
import com.example.server.Repository.BathroomAccessoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BathroomAccessoriesService {

    @Autowired
    BathroomAccessoriesRepository bathroomAccessoriesRepository;
    public List<BathroomAccessories> findAll()
    {
        return bathroomAccessoriesRepository.findAll();
    }
    public BathroomAccessories findBathroomAccessoriesById(Integer id)
    {
        return bathroomAccessoriesRepository.findBathroomAccessoriesById(id);
    }
}
