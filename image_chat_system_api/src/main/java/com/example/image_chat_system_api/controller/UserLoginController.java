package com.example.image_chat_system_api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.image_chat_system_api.service.UserLoginService;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserLoginController {
    @Autowired
    UserLoginService service;

    @PostMapping(path = "/login")
    public boolean getUserLogin(@RequestBody Map<String, String> loginData) {
    
        String mail = loginData.get("mail");
        String password = loginData.get("password");
    
        System.out.println(mail + password);
    
        // service.getPassword();
        return true;
    }
}