package com.example.image_chat_system_api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.image_chat_system_api.domain.ChatIndex;
import com.example.image_chat_system_api.repository.RoomIndexRepository;
import com.example.image_chat_system_api.viewModel.ChatRoomIndexModel;

@Service
public class RoomIndexService  {

    @Autowired RoomIndexRepository roomIndexRepository;
    
    public List<ChatIndex> roomGetAll(){

        return roomIndexRepository.findAll();
    }

    public boolean createRoom(ChatRoomIndexModel chatRoomIndexModel){
        ChatIndex chatIndex = new ChatIndex();
        chatIndex.setRoomName(chatRoomIndexModel.getRoomName());
        try {
            roomIndexRepository.save(chatIndex);

            return true; 
        } catch (Exception e) {

            return false; 
        }
    }
}