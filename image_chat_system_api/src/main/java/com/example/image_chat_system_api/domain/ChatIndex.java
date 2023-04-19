package com.example.image_chat_system_api.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chat_room_list")
public class ChatIndex {

    @Id@Getter@Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomId;

    @Getter@Setter
    private String roomName;
}
