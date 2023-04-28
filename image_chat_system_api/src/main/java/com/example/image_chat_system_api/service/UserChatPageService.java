package com.example.image_chat_system_api.service;

import java.sql.Timestamp;

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

        chatMessageList.setChat_room_id(userChatPageModel.getChat_room_id());
        chatMessageList.setChat_poster(userChatPageModel.getChat_poster());
        chatMessageList.setUserId(userChatPageModel.getUserId());
        chatMessageList.setMessage(userChatPageModel.getMessage());
        chatMessageList.setImage_path(userChatPageModel.getImage_path());
        chatMessageList.setPost_time(new Timestamp(System.currentTimeMillis()));

        System.out.println(chatMessageList.getChat_poster()+chatMessageList.getUserId()+"タイムはここですなですな");
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
