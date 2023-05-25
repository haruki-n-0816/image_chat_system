package com.example.image_chat_system_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.image_chat_system_api.service.ChatRoomNameService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ChatRoomNameController {

    @Autowired
    ChatRoomNameService ChatRoomNameService;
    
    @PostMapping("/getRoomName")
    public String chatRoomName(@RequestParam("chatRoomId") Integer chatRoomId){
        String answer = ChatRoomNameService.chatRoomName(chatRoomId);
        System.out.println(chatRoomId+"コントローラー");

        return answer;
    }
}
