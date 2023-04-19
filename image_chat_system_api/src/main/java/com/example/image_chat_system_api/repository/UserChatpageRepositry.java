package com.example.image_chat_system_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.image_chat_system_api.domain.ChatMessageList;

public interface UserChatpageRepositry extends JpaRepository<ChatMessageList,Integer> {
}
