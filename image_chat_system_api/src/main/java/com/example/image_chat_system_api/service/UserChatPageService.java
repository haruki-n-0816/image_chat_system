package com.example.image_chat_system_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.image_chat_system_api.domain.ChatMessageList;
import com.example.image_chat_system_api.repository.UserChatpageRepositry;
import com.example.image_chat_system_api.viewModel.UserChatPageModel;

@Service
public class UserChatPageService {

    @Autowired UserChatpageRepositry userChatPageRepositry;

    public boolean chatpage(UserChatPageModel userChatPageModel){
        
        ChatMessageList chatMessageList = new ChatMessageList();

        chatMessageList.setUserId(userChatPageModel.getUserId());
        chatMessageList.setChat_poster(userChatPageModel.getChat_poster());
        chatMessageList.setMessage(userChatPageModel.getMessage());
        chatMessageList.setImage_path(userChatPageModel.getImage_path());
        chatMessageList.setPost_time(userChatPageModel.getPost_time());

        System.out.println(chatMessageList.getPost_time()+"タイムはここですなですな");
        try{
            userChatPageRepositry.save(chatMessageList);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            // throw new RuntimeException("チャットメッセージの送信に失敗しました");//"チャットメッセージの送信に失敗しました"というエラーメッセージを返す
            return false;
        }
    }
}
