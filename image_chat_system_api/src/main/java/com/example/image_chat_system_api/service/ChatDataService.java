package com.example.image_chat_system_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.image_chat_system_api.domain.ChatMessageList;
import com.example.image_chat_system_api.repository.ChatDataRepository;
import com.example.image_chat_system_api.viewModel.UserDataModel;

@Service
public class ChatDataService {
    @Autowired ChatDataRepository chatDataRepository;
    
    public List<ChatMessageList>findByChatRoomId(UserDataModel userDataModel) {
        Integer chatRoomId = userDataModel.getChatRoomId();

        System.out.println(chatRoomId+"サービスはここです");

        return chatDataRepository.findByChatRoomId(chatRoomId);
    }

public boolean deleteMessage(UserDataModel userDataModel){
    Integer messageId = userDataModel.getMessageId();

    try {
        chatDataRepository.deleteById(messageId);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

}





