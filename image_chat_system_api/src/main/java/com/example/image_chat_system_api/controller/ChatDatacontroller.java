package com.example.image_chat_system_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.image_chat_system_api.service.ChatDataService;
import com.example.image_chat_system_api.viewModel.UserDataModel;
import com.example.image_chat_system_api.domain.ChatMessageList;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ChatDataController {
    @Autowired ChatDataService ChatDataService;

    @PostMapping("/chatPage")
    public List<ChatMessageList> ChatDate(@RequestBody UserDataModel UserDataModel){
        List<ChatMessageList> messageList = ChatDataService.findByChatRoomId(UserDataModel);
        System.out.println(UserDataModel+"部屋番の数字はここです");
        System.out.println(messageList+"これがデータだ！");
        return messageList;
    }
}

// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.image_chat_system_api.viewModel.UserDataModel;


// @CrossOrigin(origins = "http://localhost:8080")
// @RestController
// public class ChatDataController {
//     // @Autowired 
//     // private ChatDataRepository chatDataRepository;

//     @PostMapping("/chatPage")
//     public boolean chatData(@RequestBody UserDataModel chat_room_id){

//         System.out.println(chat_room_id.getChat_room_id());
//         return true;
//     }
// }
