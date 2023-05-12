package com.example.image_chat_system_api.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.corundumstudio.socketio.SocketIOServer;

@RestController
public class WebSocketController {
    
    @Autowired
    private SocketIOServer socketIOServer;

    @GetMapping("/chat")
    @ResponseBody
    public String socketIO() {
        return "OK";
    }

}
