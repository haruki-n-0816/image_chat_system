package com.example.image_chat_system_api.service;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.image_chat_system_api.domain.ChatMessageList;
import com.example.image_chat_system_api.repository.ChatMessagePostRepositry;
import com.example.image_chat_system_api.viewModel.UserChatPageModel;
// import com.example.image_chat_system_api.websocket.DbUpdateCheckService;

@Service
public class ChatMessagePostService {

    @Autowired ChatMessagePostRepositry chatMessagePostRepositry;
    // @Autowired DbUpdateCheckService dbUpdateCheckService;

    public boolean chatMessagePost(UserChatPageModel userChatPageModel){
        ChatMessageList chatMessageList = new ChatMessageList();

        chatMessageList.setChatRoomId(userChatPageModel.getChatRoomId());
        chatMessageList.setChatPoster(userChatPageModel.getChatPoster());
        chatMessageList.setUserId(userChatPageModel.getUserId());
        chatMessageList.setMessage(userChatPageModel.getMessage());
        chatMessageList.setImagePath(userChatPageModel.getImagePath());
        chatMessageList.setPostTime(new Timestamp(System.currentTimeMillis()));

        try{
            chatMessagePostRepositry.save(chatMessageList);
            // dbUpdateCheckService.sendCheckStatus(1);
            
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            
            return false;
        }
    }
}