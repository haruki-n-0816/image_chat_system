package com.example.image_chat_system_api.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.image_chat_system_api.service.UserChatPageService;
import com.example.image_chat_system_api.viewModel.UserChatPageModel;

@CrossOrigin(origins ="http://localhost:8080")
@RestController
public class UserChatPageController {
    
    @Autowired UserChatPageService userChatPageService;
    private static final Logger logger = LoggerFactory.getLogger(UserChatPageController.class);

    @PostMapping("/chatpage")
    public boolean chatpage(@RequestBody UserChatPageModel message){
        logger.debug("Received a message: {}", message);
        
        boolean word = userChatPageService.chatpage(message);
        logger.debug("Service response: {}", word);

        System.out.println(message.getPost_time());  
        System.out.println(word+"いけいけいけいけいけーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー！");
        return word;
    }
}
