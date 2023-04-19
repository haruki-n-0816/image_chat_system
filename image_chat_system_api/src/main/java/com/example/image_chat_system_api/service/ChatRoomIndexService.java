package com.example.image_chat_system_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.image_chat_system_api.domain.ChatIndex;
import com.example.image_chat_system_api.repository.ChatRoomRepository;
import com.example.image_chat_system_api.viewModel.ChatRoomIndexModel;

@Service
public class ChatRoomIndexService {
    @Autowired ChatRoomRepository chatRoomRepository;
    public boolean roomNameInsert(ChatRoomIndexModel chatRoomIndexModel){
        //入力したした値を持ってきたい、それをテーブルに追加したい
        ChatIndex chatIndex = new ChatIndex();
        chatIndex.setRoomName(chatRoomIndexModel.getRoomIndexName());

        try {
            chatRoomRepository.save(chatIndex);
            return true; // 処理成功
        } catch (Exception e) {
            // エラー処理
            //e.printStackTrace(); //など、エラーログの出力などの適切な処理を追加する必要があります。
            return false; // 処理失敗
        }
    }
}
