package com.example.image_chat_system_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.image_chat_system_api.service.UserLoginService;
import com.example.image_chat_system_api.viewModel.UserLoginModel;
import com.example.image_chat_system_api.viewModel.UserLoginResultModel;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserLoginController {
    
    @Autowired UserLoginService userLoginService;

    @PostMapping("/login")
    public UserLoginResultModel loginCheck(@RequestBody UserLoginModel request){
        
        System.out.println(request.getMail() + request.getPassword());

        UserLoginResultModel result = userLoginService.loginCheck(request);

        // 削除予定
        System.out.println(result.getResult());
        System.out.println(result.getUserId());
        System.out.println(result.getUserName());

        return result;
    }
}
