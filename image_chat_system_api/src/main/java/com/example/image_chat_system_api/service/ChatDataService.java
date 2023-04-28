package com.example.image_chat_system_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.image_chat_system_api.domain.ChatDataList;
import com.example.image_chat_system_api.repository.ChatDataRepository;

@Service
public class ChatDataService {
    @Autowired ChatDataRepository chatDataRepository;
    
    public List<ChatDataList> getByChatRoomId(int chat_room_id) {
        return chatDataRepository.findByChatRoomId(chat_room_id);
    }
}
