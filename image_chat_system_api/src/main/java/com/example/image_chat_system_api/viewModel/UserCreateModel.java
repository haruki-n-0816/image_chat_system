package com.example.image_chat_system_api.viewModel;

import lombok.Getter;
import lombok.Setter;

public class UserCreateModel {

    @Getter@Setter
    private String name;

    @Getter@Setter
    private String mail;
    
    @Getter@Setter
    private String password;
}
