package com.example.image_chat_system_api.service;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.image_chat_system_api.domain.ChatMessageList;
import com.example.image_chat_system_api.repository.ChatImagePostRepository;

@Service
public class ChatImagePostService {

    @Autowired ChatImagePostRepository chatImagePostRepositry;

    public boolean chatImagePost(int chatRoomId,String chatPoster,String userId,String imagePath){
        ChatMessageList chatMessageList = new ChatMessageList();

        chatMessageList.setChatRoomId(chatRoomId);
        chatMessageList.setChatPoster(chatPoster);
        chatMessageList.setUserId(userId);
        chatMessageList.setImagePath(imagePath);
        chatMessageList.setPostTime(new Timestamp(System.currentTimeMillis()));

        try{
            chatImagePostRepositry.save(chatMessageList);

            return true;
        } catch(Exception e) {
            e.printStackTrace();
            
            return false;
        }
    }
}