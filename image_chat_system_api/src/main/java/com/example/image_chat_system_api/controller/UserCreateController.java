package com.example.image_chat_system_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.image_chat_system_api.service.UserCreateService;
import com.example.image_chat_system_api.viewModel.UserCreateModel;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserCreateController {

    @Autowired UserCreateService userCreateService;
    
    @PostMapping("/create")
    public boolean userCreate(@RequestBody UserCreateModel userCreateModel){

        return userCreateService.userCreate(userCreateModel);
    }
}