// package com.example.image_chat_system_api.websocket;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.socket.config.annotation.EnableWebSocket;
// import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
// import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

// @Configuration
// @EnableWebSocket
// public class SocketIOConfig implements WebSocketConfigurer {

//     @Override
//     public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//         registry.addHandler(socketIOEventHandlerRetern(), "/socket.io").setAllowedOrigins("*");
//     }

//     @Bean
//     public SocketIOEventHandler socketIOEventHandlerRetern() {
//         return new SocketIOEventHandler();
//     }
// }