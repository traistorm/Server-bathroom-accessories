package com.example.server.Service;

import com.example.server.Entity.BathroomAccessories;
import com.example.server.Repository.BathroomAccessoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
    public List<BathroomAccessories> finAllInPage(Integer page, Integer itemsPerPage)
    {
        List<BathroomAccessories> bathroomAccessoriesList = bathroomAccessoriesRepository.findAll();
        if ((page - 1) * itemsPerPage + itemsPerPage > bathroomAccessoriesList.size())
        {
            return bathroomAccessoriesList.subList((page - 1) * itemsPerPage, bathroomAccessoriesList.size());
        }
        return bathroomAccessoriesList.subList((page - 1) * itemsPerPage, (page - 1) * itemsPerPage + itemsPerPage);
    }
    public BathroomAccessories findBathroomAccessoriesById(Integer id)
    {
        return bathroomAccessoriesRepository.findBathroomAccessoriesById(id);
    }
}
