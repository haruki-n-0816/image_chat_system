package com.example.image_chat_system_api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.image_chat_system_api.domain.ChatIndex;
import com.example.image_chat_system_api.repository.RoomIndexRepository;

@Service
public class RoomIndexService  {

    @Autowired RoomIndexRepository roomIndexRepository;
    
    public List<ChatIndex> roomGetAll(){

        return roomIndexRepository.findAll();
    }

    public boolean createRoom(ChatIndex chatIndex){
        Optional<ChatIndex> roomNameList = roomIndexRepository.findByRoomName(chatIndex.getRoomName());
        if(!roomNameList.isPresent()){
            roomIndexRepository.save(chatIndex);

            return true;
        }else{
            
            return false;
        }
    }
}