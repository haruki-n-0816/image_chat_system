package com.example.image_chat_system_api.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.image_chat_system_api.domain.UserList;
import com.example.image_chat_system_api.repository.UserCreateRepositry;
import com.example.image_chat_system_api.viewModel.UserCreateModel;

@Service
public class UserCreateServise {

    @Autowired UserCreateRepositry userCreateRepositry;
    
    public boolean userCreate(UserCreateModel userCreateModel){
        
        String id = UUID.randomUUID().toString();
        
        UserList userList = new UserList();
        
        userList.setUserId(id);
        userList.setUserName(userCreateModel.getName());
        userList.setUserMail(userCreateModel.getMail());
        userList.setUserPassword(userCreateModel.getPassword());

        System.out.print(id);
        try {
            
            userCreateRepositry.save(userList);
            
            return true;
            } catch (Exception e) {
            
            e.printStackTrace();

            return false;
        }
    }
}
