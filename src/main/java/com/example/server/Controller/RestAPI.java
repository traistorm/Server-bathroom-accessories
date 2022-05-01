package com.example.server.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestAPI {
    @RequestMapping("/CheckKey")
    public String checkKey()
    {
        return "Test";
    }
}
