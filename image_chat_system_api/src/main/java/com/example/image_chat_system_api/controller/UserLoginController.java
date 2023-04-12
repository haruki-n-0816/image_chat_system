package com.example.image_chat_system_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.image_chat_system_api.service.UserLoginService;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class UserLoginController {
    @Autowired
    UserLoginService service;

    @PostMapping(path="/login")
    public boolean getUserLogin(@RequestParam("mail")String mail,@RequestParam("password")String password){
        return service.getPassword(mail, password);
    }
}
