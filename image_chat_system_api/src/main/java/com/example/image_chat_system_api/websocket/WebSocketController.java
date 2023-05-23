package com.example.image_chat_system_api.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public String handleMessage(String message) {
        
        return "サーバーからのレスポンス: " + message;
    }
}