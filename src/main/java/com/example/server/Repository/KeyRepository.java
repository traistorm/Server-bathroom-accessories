package com.example.server.Repository;

import com.example.server.Entity.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends JpaRepository<Key, Integer> {
    Key findKeyByValue(String value);
}
