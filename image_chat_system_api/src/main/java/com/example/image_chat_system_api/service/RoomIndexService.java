package com.example.image_chat_system_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.image_chat_system_api.domain.ChatIndex;



@Service
public class RoomIndex {
    @Autowired RoomIndex roomrepo;
    
    public List<ChatIndex> getAll(){
        return roomrepo.findAll();
    }
}

