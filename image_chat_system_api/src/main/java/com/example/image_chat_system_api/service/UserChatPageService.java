package com.example.image_chat_system_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.image_chat_system_api.domain.ChatMessageList;
import com.example.image_chat_system_api.domain.UserList;
import com.example.image_chat_system_api.repository.UserChatpageRepositry;
import com.example.image_chat_system_api.viewModel.UserChatPageModel;

@Service
public class UserChatPageService {

    @Autowired UserChatpageRepositry userChatPageRepositry;

    public boolean chatpage(UserChatPageModel userChatPageModel){
        
        ChatMessageList chatMessageList = new ChatMessageList();
        UserList userList = new UserList();

        userList.setId(userChatPageModel.getId());
        chatMessageList.setMessage(userChatPageModel.getMessage());
        chatMessageList.setImage_path(userChatPageModel.getImage_path());
        chatMessageList.setPost_time(userChatPageModel.getPost_time());

        System.out.println(chatMessageList);
        try{
            userChatPageRepositry.save(chatMessageList);
            return true;
        } catch(Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}
