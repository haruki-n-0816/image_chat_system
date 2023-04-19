package com.example.image_chat_system_api.viewModel;

import lombok.Getter;
import lombok.Setter;

public class UserLoginResultModel {
    
    @Getter@Setter
    private Boolean result;

    @Getter@Setter
    private String userId;

    @Getter@Setter
    private String userName; 
}
