package com.example.image_chat_system_api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_list")
public class UserList {

    @Id@Getter@Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter@Setter
    private String userId;

    @Getter@Setter
    private String userName; 

    @Getter@Setter
    private String userMail;

    @Getter@Setter
    private String userPassword;
}