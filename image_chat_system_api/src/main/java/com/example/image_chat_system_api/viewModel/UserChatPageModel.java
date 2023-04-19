package com.example.image_chat_system_api.viewModel;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

public class UserChatPageModel {
    @Id@Getter@Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter@Setter
    private int chat_poster;
    @Getter@Setter
    private String message;
    @Getter@Setter
    private String image_path;
    @Getter@Setter
    private Timestamp post_time;
}
