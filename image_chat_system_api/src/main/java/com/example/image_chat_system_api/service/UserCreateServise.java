package com.example.image_chat_system_api.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.image_chat_system_api.domain.UserList;
import com.example.image_chat_system_api.repository.UserCreateRepositry;
import com.example.image_chat_system_api.viewModel.UserCreateModel;

public class UserCreateServise {
    @Autowired UserCreateRepositry userCreateRepositry;
    public boolean userCreate(UserCreateModel userCreateModel){
        //入力したした値を持ってきたい、それをテーブルに追加したい
        String id = UUID.randomUUID().toString();
        UserList userList = new UserList();
        userList.setUserId(id);
        userList.setUserName(userCreateModel.getUserName());
        userList.setUserMail(userCreateModel.getMail());
        userList.setUserPassword(userCreateModel.getPassword());

        try {
            userCreateRepositry.save(userList);
            return true; // 処理成功
        } catch (Exception e) {
            // エラー処理
            // e.printStackTrace(); など、エラーログの出力などの適切な処理を追加する必要があります。
            return false; // 処理失敗
        }
    }
}
