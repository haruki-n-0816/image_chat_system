package com.example.image_chat_system_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.image_chat_system_api.domain.UserLogin;
import com.example.image_chat_system_api.repository.UserLoginRepository;
import com.example.image_chat_system_api.viewModel.UserLoginModel;

@Service
public class UserLoginService {
    
    @Autowired UserLoginRepository userLoginRepository;

    public Boolean loginCheck(UserLoginModel request){

    Optional<UserLogin> optionalUserLogin = userLoginRepository.findByUserMail(request.getMail());
    
    boolean result = false;
    if (request.getPassword().equals(optionalUserLogin.get().getUserPassword())) {
        result = true;
    }
    return result;
    }
}
