package com.example.image_chat_system_api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.image_chat_system_api.domain.ChatIndex;
import com.example.image_chat_system_api.service.RoomIndexService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RoomIndexController {

    @Autowired RoomIndexService roomIndexService;

    @GetMapping("/roomIndex")
    public List<ChatIndex> createRoomName() {

        return roomIndexService.roomGetAll();    
    }

    @PostMapping("/chatIndex")
    public boolean checkRoomName(@RequestBody ChatIndex chatIndex){
        
        return roomIndexService.createRoom(chatIndex);
    }
}