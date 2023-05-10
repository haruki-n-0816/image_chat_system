package com.example.image_chat_system_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.image_chat_system_api.domain.ChatMessageList;

@Repository
public interface ChatMessagePostRepositry extends JpaRepository<ChatMessageList,Integer> {
}