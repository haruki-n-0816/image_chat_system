package com.example.image_chat_system_api.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.image_chat_system_api.domain.UserList;
import com.example.image_chat_system_api.repository.UserLoginRepository;
import com.example.image_chat_system_api.viewModel.UserLoginModel;
import com.example.image_chat_system_api.viewModel.UserLoginResultModel;

@Service
public class UserLoginService {
    
    @Autowired UserLoginRepository userLoginRepository;

    public UserLoginResultModel loginCheck(UserLoginModel request){
    Optional<UserList> optionalUserLogin = userLoginRepository.findByUserMail(request.getMail());
    UserLoginResultModel result = new UserLoginResultModel();
    if (optionalUserLogin.isPresent() && request.getPassword().equals(optionalUserLogin.get().getUserPassword())) {
        result.setResult(true);
        result.setUserId(optionalUserLogin.get().getUserId());
        result.setUserName(optionalUserLogin.get().getUserName());
    }
    
    return result;
    }
}