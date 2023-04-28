package com.example.image_chat_system_api.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.image_chat_system_api.domain.UserList;
import com.example.image_chat_system_api.repository.UserCreateRepositry;
import com.example.image_chat_system_api.repository.UserLoginRepository;
import com.example.image_chat_system_api.viewModel.UserCreateModel;

@Service
public class UserCreateServise {

    @Autowired
    UserCreateRepositry userCreateRepositry;
    @Autowired
    UserLoginRepository userLoginRepository;

    public boolean userCreate(UserCreateModel userCreateModel) {

        String id = UUID.randomUUID().toString();
        UserList userList = new UserList();
        // メールアドレスの重複チェック
        UserList existingUser = userCreateRepositry.findByUserMail(userCreateModel.getMail());
        if (existingUser == null) {

            userList.setUserMail(userCreateModel.getMail());
            userList.setUserId(id);
            userList.setUserName(userCreateModel.getName());
            userList.setUserPassword(userCreateModel.getPassword());

            userCreateRepositry.save(userList);

            return true;
        }else{
            return false;
        }
    }
}
