package com.example.image_chat_system_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.image_chat_system_api.service.ChatMessagePostService;
import com.example.image_chat_system_api.viewModel.UserChatPageModel;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ChatMessagePostController {

    @Autowired
    ChatMessagePostService chatMessagePostService;

    @PostMapping("/chatPagePost")
    public boolean chatMessagePost(@RequestBody UserChatPageModel message) {
        System.out.println(message.getUserId()+"ここですここ");
        boolean result = chatMessagePostService.chatMessagePost(message);

        return result;
    }
}