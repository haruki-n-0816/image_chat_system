package com.example.image_chat_system_api.viewModel;

import lombok.Getter;
import lombok.Setter;

public class UserChatPageModel {
    @Getter@Setter
    private int chat_room_id;

    @Getter@Setter
    private String chat_poster;
    
    @Getter@Setter
    private String userId;
    
    @Getter@Setter
    private String message;
    
    @Getter@Setter
    private String image_path;
    // @Getter@Setter
    // private Timestamp post_time;
    // private Timestamp post_time = new Timestamp(System.currentTimeMillis());

}
