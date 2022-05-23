package com.example.server.Service;

import com.example.server.Entity.BathroomAccessories;
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
    public List<CoatHanger> finAllInPage(Integer page, Integer itemsPerPage)
    {
        List<CoatHanger> coatHangerList = coatHangerRepository.findAll();
        if ((page - 1) * itemsPerPage + itemsPerPage > coatHangerList.size())
        {
            return coatHangerList.subList((page - 1) * itemsPerPage, coatHangerList.size());
        }
        return coatHangerList.subList((page - 1) * itemsPerPage, (page - 1) * itemsPerPage + itemsPerPage);
    }

    public List<CoatHanger> findCoatHangerByNewpriceBetween(Integer minRange, Integer maxRange, Integer page, Integer itemsPerPage)
    {
        List<CoatHanger> coatHangerList = coatHangerRepository.findCoatHangerByNewpriceBetween(minRange, maxRange);
        if ((page - 1) * itemsPerPage + itemsPerPage > coatHangerList.size())
        {
            return coatHangerList.subList((page - 1) * itemsPerPage, coatHangerList.size());
        }
        return coatHangerList.subList((page - 1) * itemsPerPage, (page - 1) * itemsPerPage + itemsPerPage);
    }
    public List<CoatHanger> findCoatHangerByNewpriceBetweenOrderByMostViewDesc(Integer minRange, Integer maxRange, Integer page, Integer itemsPerPage)
    {
        List<CoatHanger> coatHangerList = coatHangerRepository.findAll();
        if ((page - 1) * itemsPerPage + itemsPerPage > coatHangerList.size())
        {
            return coatHangerList.subList((page - 1) * itemsPerPage, coatHangerList.size());
        }
        return coatHangerList.subList((page - 1) * itemsPerPage, (page - 1) * itemsPerPage + itemsPerPage);
    }
    public List<CoatHanger> findCoatHangerByNewpriceBetweenOrderByNewpriceAsc(Integer minRange, Integer maxRange, Integer page, Integer itemsPerPage)
    {
        List<CoatHanger> coatHangerList = coatHangerRepository.findCoatHangerByNewpriceBetweenOrderByNewpriceAsc(minRange, maxRange);

        if ((page - 1) * itemsPerPage + itemsPerPage > coatHangerList.size())
        {
            return coatHangerList.subList((page - 1) * itemsPerPage, coatHangerList.size());
        }
        return coatHangerList.subList((page - 1) * itemsPerPage, (page - 1) * itemsPerPage + itemsPerPage);
    }
    public List<CoatHanger> findCoatHangerByNewpriceBetweenOrderByNewpriceDesc(Integer minRange, Integer maxRange, Integer page, Integer itemsPerPage)
    {
        List<CoatHanger> coatHangerList = coatHangerRepository.findCoatHangerByNewpriceBetweenOrderByNewpriceDesc(minRange, maxRange);
        if ((page - 1) * itemsPerPage + itemsPerPage > coatHangerList.size())
        {
            return coatHangerList.subList((page - 1) * itemsPerPage, coatHangerList.size());
        }
        return coatHangerList.subList((page - 1) * itemsPerPage, (page - 1) * itemsPerPage + itemsPerPage);
    }
}
