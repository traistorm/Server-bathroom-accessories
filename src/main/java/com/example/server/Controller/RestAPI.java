package com.example.server.Controller;

import com.example.server.Entity.Key;
import com.example.server.Entity.Student;
import com.example.server.Repository.StudentRepository;
import com.example.server.Service.KeyService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public JSONObject checkKey()
    {
        String json = "{\n" +
                "  \"items\": [\n" +
                "    { \"id\": 1, \"name\": \"Apples\",  \"price\": \"$2\" },\n" +
                "    { \"id\": 2, \"name\": \"Peaches\", \"price\": \"$5\" }\n" +
                "  ] \n" +
                "}";
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject;
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
    @PostMapping("testToken")
    //public String testToken(@RequestHeader (name="Authorization") String headerToken)
    public String testToken(@RequestParam(name = "token", required = false) String token)
    {
        token = token.replaceAll(" ", "+");
        return tokenAuthentication(token);
        //return tokenAuthentication(headerToken.substring(7, headerToken.length()));
    }
}
