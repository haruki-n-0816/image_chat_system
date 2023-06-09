package com.example.image_chat_system_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.image_chat_system_api.domain.ChatIndex;

@Repository
public interface ChatRoomNameRepositry extends JpaRepository<ChatIndex,Integer>,JpaSpecificationExecutor<ChatIndex>{

}
