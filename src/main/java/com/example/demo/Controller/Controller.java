package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    @GetMapping("/test")
    public String Test() {
        return "Test Spring Boot!";
    }

}
