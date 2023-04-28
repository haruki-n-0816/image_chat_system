package com.example.image_chat_system_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.image_chat_system_api.domain.ChatDataList;


@Repository
public interface ChatDataRepository  extends JpaRepository<ChatDataList,Integer>{
    List<ChatDataList> findByChatRoomId(int chat_room_id);
}

