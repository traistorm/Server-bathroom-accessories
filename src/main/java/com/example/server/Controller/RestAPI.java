package com.example.server.Controller;

import com.example.server.Entity.Student;
import com.example.server.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestAPI {
    @Autowired
    StudentRepository studentRepository;
    @RequestMapping("/CheckKey")
    public String checkKey()
    {

        return "Test";
    }
    @PostMapping("key")
    public ResponseEntity<Student> Key()
    {
        Student student = new Student();
        student.setId(0);
        student.setName("Cuong Cao Huy");
        studentRepository.save(student);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }
}