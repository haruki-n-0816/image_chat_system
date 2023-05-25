package com.example.image_chat_system_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.image_chat_system_api.domain.ChatIndex;
import com.example.image_chat_system_api.repository.ChatRoomNameRepositry;

@Service
public class ChatRoomNameService {

    @Autowired ChatRoomNameRepositry chatRoomNameRepositry;

    public String chatRoomName(Integer chatRoomId){
        Optional<ChatIndex> chatRoomName= chatRoomNameRepositry.findById(chatRoomId);
        return chatRoomName.map(ChatIndex::getRoomName).orElse("");
    }    
}
