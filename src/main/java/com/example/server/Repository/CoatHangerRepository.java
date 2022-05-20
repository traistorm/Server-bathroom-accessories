package com.example.server.Repository;

import com.example.server.Entity.CoatHanger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoatHangerRepository extends JpaRepository<CoatHanger, Integer> {
    CoatHanger findCoatHangerById(Integer id);
}
