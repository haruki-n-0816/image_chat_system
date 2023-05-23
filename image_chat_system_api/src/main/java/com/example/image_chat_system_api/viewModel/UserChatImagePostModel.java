package com.example.image_chat_system_api.viewModel;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

public class UserChatImagePostModel {
    @Getter@Setter
    private int chatRoomId;

    @Getter@Setter
    private String chatPoster;
    
    @Getter@Setter
    private String userId;
    
    @Getter@Setter
    private String message;
    
    @Getter@Setter
    private MultipartFile imagePath;
}