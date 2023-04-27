package com.example.image_chat_system_api.controller;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.image_chat_system_api.service.ChatRoomIndexService;
import com.example.image_chat_system_api.viewModel.ChatRoomIndexModel;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ChatRoomController {
    @Autowired ChatRoomIndexService chatRoomIndexService;
    
    @PostMapping("/chatindex")
    public boolean roomNameInsert(@RequestBody ChatRoomIndexModel chatRoomIndexModel){
        
        System.out.println(chatRoomIndexModel.getRoomName());

        boolean result = chatRoomIndexService.roomNameInsert(chatRoomIndexModel);

        System.out.println(result);

        return result;
    }
}
