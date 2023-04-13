package com.example.image_chat_system_api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.image_chat_system_api.domain.UserLogin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserLoginController {
    
    @PostMapping("/login")
    public boolean loginCheck(@RequestBody UserLogin request){
        System.out.println(request.getMail() + request.getPassword());
        return true;
    }
}
