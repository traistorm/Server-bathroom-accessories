package com.example.server.Service;

import com.example.server.Entity.Key;
import com.example.server.Repository.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyService {
    @Autowired
    KeyRepository keyRepository;
    public Key findKeyByValue(String value)
    {
        return keyRepository.findKeyByValue(value);
    }
}
