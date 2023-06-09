package com.example.image_chat_system_api.service;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.image_chat_system_api.domain.ChatMessageList;
import com.example.image_chat_system_api.repository.ChatMessagePostRepositry;
import com.example.image_chat_system_api.viewModel.UserChatPageModel;

@Service
public class ChatMessagePostService {

    @Autowired ChatMessagePostRepositry chatMessagePostRepositry;

    public boolean chatMessagePost(UserChatPageModel userChatPageModel){
        ChatMessageList chatMessageList = new ChatMessageList();

        chatMessageList.setChatRoomId(userChatPageModel.getChatRoomId());
        chatMessageList.setChatPoster(userChatPageModel.getChatPoster());
        chatMessageList.setUserId(userChatPageModel.getUserId());
        chatMessageList.setMessage(userChatPageModel.getMessage());
        chatMessageList.setImagePath(userChatPageModel.getImagePath());
        chatMessageList.setPostTime(new Timestamp(System.currentTimeMillis()));

        // String userId = userChatPageModel.getUserId();
        // userId = userId.substring(1, userId.length() - 1);
        // chatMessageList.setUserId(userId);
        try{
            chatMessagePostRepositry.save(chatMessageList);

            return true;
        } catch(Exception e) {
            e.printStackTrace();
            
            return false;
        }
    }
}