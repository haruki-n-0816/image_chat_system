package com.example.image_chat_system_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.image_chat_system_api.service.UserCreateServise;
import com.example.image_chat_system_api.viewModel.UserCreateModel;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserCreateController {
    @Autowired UserCreateServise userCreateServise;
    
    @PostMapping("/create")
    public boolean userCreate(@RequestBody UserCreateModel userCreateModel){
        
        System.out.println(userCreateModel.getName() + userCreateModel.getMail() + userCreateModel.getPassword());

        boolean result = userCreateServise.userCreate(userCreateModel);

        System.out.println(result);

        return result;
    }

    // public class chatcon {
    //     @Autowired catser chatservise;
        
    //     @PostMapping("/chatindex")
    //     public boolean roomName(@RequestBody ChatIndex chatindex){
            
    //         System.out.println(chatindex.getRoomName() );
    
    //         boolean result = chatservise.roomName(chatindex);
    
    //         System.out.println(result);
    
    //         return result;
    //     }
    // }
}
