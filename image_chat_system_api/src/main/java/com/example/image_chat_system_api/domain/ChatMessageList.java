package com.example.image_chat_system_api.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chat_history")
public class ChatMessageList {
    
    @Id@Getter@Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter@Setter
    private int chatRoomId;
    
    @Getter@Setter
    private String chatPoster;
    
    @Getter@Setter
    private String userId;
    
    @Getter@Setter
    private String message;
    
    @Getter@Setter
    private String imagePath;
    
    @Getter@Setter
    private Timestamp postTime;
}
