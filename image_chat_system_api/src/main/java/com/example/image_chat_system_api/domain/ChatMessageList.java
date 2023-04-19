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
@Table(name = "user_chat_page")
public class ChatMessageList {
    
    @Id@Getter@Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter@Setter
    private String message;
    @Getter@Setter
    private String image_path;
    @Getter@Setter
    private Timestamp post_time;
}
