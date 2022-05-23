package com.example.server.Repository;

import com.example.server.Entity.BathroomAccessories;
import com.example.server.Entity.CoatHanger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoatHangerRepository extends JpaRepository<CoatHanger, Integer> {
    CoatHanger findCoatHangerById(Integer id);
    List<CoatHanger> findCoatHangerByNewpriceBetween(Integer minRange, Integer maxRange);
    List<CoatHanger> findCoatHangerByNewpriceBetweenOrderByNewpriceAsc(Integer minRange, Integer maxRange);
    List<CoatHanger> findCoatHangerByNewpriceBetweenOrderByNewpriceDesc(Integer minRange, Integer maxRange);
}
