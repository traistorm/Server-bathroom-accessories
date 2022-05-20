package com.example.server.Repository;

import com.example.server.Entity.BathroomAccessories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BathroomAccessoriesRepository extends JpaRepository<BathroomAccessories, Integer>
{
    BathroomAccessories findBathroomAccessoriesById(Integer id);
}
