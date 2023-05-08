package com.example.image_chat_system_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.image_chat_system_api.domain.ChatMessageList;

@Repository
public interface ChatDataRepository extends JpaRepository<ChatMessageList,Integer>{
    List<ChatMessageList> findByChatRoomId(Integer chat_room_id);
}

