package com.example.image_chat_system_api.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.image_chat_system_api.domain.UserList;
import com.example.image_chat_system_api.repository.UserCreateRepositry;
import com.example.image_chat_system_api.repository.UserLoginRepository;
import com.example.image_chat_system_api.viewModel.UserCreateModel;

// @Service
// public class UserCreateServise {

//     @Autowired UserCreateRepositry userCreateRepositry;
    
//     public boolean userCreate(UserCreateModel userCreateModel){
        
//         String id = UUID.randomUUID().toString();
        
//         UserList userList = new UserList();
        
//         userList.setUserId(id);
//         userList.setUserName(userCreateModel.getName());
//         userList.setUserMail(userCreateModel.getMail());
//         userList.setUserPassword(userCreateModel.getPassword());

//         System.out.print(id);
//         try {
            
//             userCreateRepositry.save(userList);
            
//             return true;
//         } catch (Exception e) {
            
//             e.printStackTrace();

//             return false;
//         }
//     }
// }
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
        if (existingUser != null) {
            throw new RuntimeException("既に登録されたメールアドレスです");
        }else{
            userList.setUserMail(userCreateModel.getMail());
        }

        userList.setUserId(id);
        userList.setUserName(userCreateModel.getName());
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

    // メールアドレスを検索するためのメソッド
    // public UserList findByUserMail(String userMail) {
    //     return userCreateRepositry.findByUserMail(userMail);
    // }
}
