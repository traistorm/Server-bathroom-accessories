package com.example.server.Controller;

import com.example.server.Entity.Key;
import com.example.server.Entity.Student;
import com.example.server.Repository.StudentRepository;
import com.example.server.Service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.server.Utilities.Function.tokenAuthentication;
import static com.example.server.Utilities.Function.tokenInitialization;

@RestController
@RequestMapping("/api")
public class RestAPI {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    KeyService keyService;
    @RequestMapping("/CheckKey")
    public String checkKey()
    {

        return "Test";
    }

    @PostMapping("activeKey/{keyValue}/{token}")
    public ResponseEntity<String> activeKey(@PathVariable("keyValue") String keyValue,
                                         @PathVariable("token") String token)
    {
        if (tokenAuthentication(token))
        {
            Key key = keyService.findKeyByValue(keyValue);
            if (key != null)
            {
                return new ResponseEntity<String>("Activated", HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<String>("Key not found", HttpStatus.NOT_FOUND);
            }

        }
        else
        {
            return new ResponseEntity<String>("Token is wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("key")
    public ResponseEntity<Student> key(@RequestParam String token)
    {
        System.out.println(token);
        Student student = new Student();
        student.setId(0);
        student.setName(token);
        studentRepository.save(student);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }
    @PostMapping("login")
    public ResponseEntity<String> login()
    {
        String token = tokenInitialization();
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }
}
