package com.example.image_chat_system_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.image_chat_system_api.domain.ChatDataList;
import com.example.image_chat_system_api.service.ChatDataService;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ChatDatacontroller {
    @Autowired ChatDataService ChatDataService;

    @PostMapping("/chatpagenext")
    public List<ChatDataList> ChatDate(@PathVariable int chat_room_id){
        List<ChatDataList> messageList = ChatDataService.getByChatRoomId(chat_room_id);
        System.out.println(chat_room_id+"おおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおお");
        System.out.println(messageList+"これがデータだ！！！！！！！！！！！！！！！！！！！！！");
        return messageList;
    }
}
 