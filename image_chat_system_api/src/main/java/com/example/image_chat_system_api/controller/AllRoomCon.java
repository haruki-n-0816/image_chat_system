package com.example.image_chat_system_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.image_chat_system_api.domain.ChatIndex;
import com.example.image_chat_system_api.service.AllRoomSer;

// @CrossOrigin(origins = "http://localhost:8080")
// //@RestController
// public class AllRoomCon {
//     @Autowired AllRoomSer roomser;

//     @GetMapping("/chatindex2")
//     public List<ChatIndex> roomNameInsert() {
//         System.out.println(roomser.getAll());
//         return roomser.getAll();    
//     }
// }
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AllRoomCon {
    @Autowired AllRoomSer roomser;

    @GetMapping("/chatindex2")
    public List<ChatIndex> roomNameInsert() {
        System.out.println(roomser.getAll());
        return roomser.getAll();    
    }
}
