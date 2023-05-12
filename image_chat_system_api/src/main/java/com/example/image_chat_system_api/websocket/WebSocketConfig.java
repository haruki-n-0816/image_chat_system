// package com.example.image_chat_system_api.websocket;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.socket.config.annotation.EnableWebSocket;
// import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
// import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

// @CrossOrigin(origins = "http://localhost:8080")
// @Configuration
// @EnableWebSocket
// public class WebSocketConfig implements WebSocketConfigurer{
    
//     @Override
//     public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//         registry.addHandler(new MessageTimingSendHandler(null), "/chat").setAllowedOrigins("http://localhost:8080");
//     }
//     @Bean
//     public MessageTimingSendHandler myHandler() {
//         return new MessageTimingSendHandler(null);
//     }
// }
