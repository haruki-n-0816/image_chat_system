package com.example.image_chat_system_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.image_chat_system_api.domain.UserLogin;
import com.example.image_chat_system_api.repository.UserLoginRepository;

@Service
public class UserLoginService {
    @Autowired
    UserLoginRepository userLoginRepository;

    public boolean getPassword (String mail,String password){
        UserLogin result = userLoginRepository.findByUserMail(mail).orElse(null);
        String getpassword = result.getUserPassword();
        System.out.println(getpassword);
        boolean a = true;
        return a;
    }
}
