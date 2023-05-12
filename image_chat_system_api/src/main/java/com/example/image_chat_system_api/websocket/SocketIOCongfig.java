package com.example.image_chat_system_api.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

@Configuration
public class SocketIOCongfig {
    
    @Autowired
    private SocketIOEventHandler socketIOEventHandler;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("localhost");
        config.setPort(8081);
        config.setContext("/socket.io");
        SocketIOServer server = new SocketIOServer(config);
        server.addListeners(socketIOEventHandler);
        return server;
    }
}
