// package com.example.image_chat_system_api.websocket;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.socket.CloseStatus;
// import org.springframework.web.socket.WebSocketSession;
// import org.springframework.web.socket.handler.TextWebSocketHandler;

// public class MessageTimingSendHandler extends TextWebSocketHandler{
//     private final DbUpdateCheckService dbUpdateCheckService;

//     @Autowired
//     public MessageTimingSendHandler(DbUpdateCheckService dbUpdateCheckService) {
//         this.dbUpdateCheckService = dbUpdateCheckService;
//     }

//     @Override
//     public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//         this.dbUpdateCheckService.addSession(session);
//     }

//     @Override
//     public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//         this.dbUpdateCheckService.removeSession(session);
//     }
// }
