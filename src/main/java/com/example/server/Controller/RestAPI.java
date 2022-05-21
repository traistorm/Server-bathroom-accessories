package com.example.server.Controller;

import com.example.server.Entity.*;
import com.example.server.Repository.BathroomAccessoriesRepository;
import com.example.server.Repository.CoatHangerRepository;
import com.example.server.Repository.NewsRepository;
import com.example.server.Repository.StudentRepository;
import com.example.server.Service.*;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.server.Utilities.Function.tokenAuthentication;
import static com.example.server.Utilities.Function.tokenInitialization;


@RestController
@RequestMapping("/api/v1")
public class RestAPI {
    @Autowired
    StudentService studentService;
    @Autowired
    KeyService keyService;
    @Autowired
    BathroomAccessoriesService bathroomAccessoriesService;
    @Autowired
    CoatHangerService coatHangerService;
    @Autowired
    NewsService newsService;
    //@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/CheckKey")
    @ResponseBody
    public List<Student> checkKey() {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student();
        student.setId(2222);
        student.setName("Cuong");
        studentList.add(student);
        studentList.add(student);
        return studentList;
    }

    /*@PostMapping("activeKey")
    public ResponseEntity<String> activeKey(@RequestParam("keyValue") String keyValue,
                                         @RequestParam("token") String token)
    {
        int statusTokenAuthentication = tokenAuthentication(token);
        if (statusTokenAuthentication == 2)
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
        else if (statusTokenAuthentication == 1)
        {
            return new ResponseEntity<String>("Token is expiration", HttpStatus.BAD_REQUEST);
        }
        else if (statusTokenAuthentication == 0)
        {
            return new ResponseEntity<String>("Login info is wrong", HttpStatus.BAD_REQUEST);
        }
        else
        {
            return new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST);
        }
    }*/

    @PostMapping("key1")
    public ResponseEntity<Student> key(@RequestParam String token) {
        System.out.println(token);
        Student student = new Student();
        student.setId(0);
        student.setName(token);
        //studentService.save(student);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<String> login() {
        String token = tokenInitialization();
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    @PostMapping("testToken")
    //public String testToken(@RequestHeader (name="Authorization") String headerToken)
    public String testToken(@RequestParam(name = "token", required = false) String token) {
        token = token.replaceAll(" ", "+");
        return tokenAuthentication(token);
        //return tokenAuthentication(headerToken.substring(7, headerToken.length()));
    }

    // API for Bathroom Accessories

    @GetMapping(value = "/bathroomaccessories", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<List<BathroomAccessories>> findAllBathroomAccessories() // Lấy danh sách tất cả các sản phẩm
    {
        List<BathroomAccessories> bathroomAccessoriesList = bathroomAccessoriesService.findAll();
        if (bathroomAccessoriesList.size() > 0)
        {
            return new ResponseEntity<>(bathroomAccessoriesList, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/bathroomaccessories/{id}", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<BathroomAccessories> findBathroomAccessoriesById(@PathVariable("id") Integer id) // Lấy một sản phẩm bằng id
    {
        BathroomAccessories bathroomAccessories = bathroomAccessoriesService.findBathroomAccessoriesById(id);
        if (bathroomAccessories != null)
        {
            return new ResponseEntity<>(bathroomAccessories, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //

    // Api for Coat Hanger
    @GetMapping(value = "/coathanger", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<List<CoatHanger>> findAllCoatHanger() // Lấy danh sách tất cả các loại mắc áo
    {
        List<CoatHanger> coatHangerList = coatHangerService.findAll();
        if (coatHangerList.size() > 0)
        {
            return new ResponseEntity<>(coatHangerList, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/coathanger/{id}", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<CoatHanger> findCoatHangerById(@PathVariable("id") Integer id) // Lấy một mắc áo bằng id
    {
        CoatHanger coatHanger = coatHangerService.findCoatHangerById(id);
        if (coatHanger != null)
        {
            return new ResponseEntity<>(coatHanger, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //

    // Api for News
    @GetMapping(value = "/news", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<List<News>> findAllNews() // Lấy danh sách tất cả các loại mắc áo
    {
        List<News> newsList = newsService.findAll();
        if (newsList.size() > 0)
        {
            return new ResponseEntity<>(newsList, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/news/{id}", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<News> findNewsById(@PathVariable("id") Integer id) // Lấy một mắc áo bằng id
    {
        News news = newsService.findNewsById(id);
        if (news != null)
        {
            return new ResponseEntity<>(news, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //
}
