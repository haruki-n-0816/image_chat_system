// package com.example.image_chat_system_api.websocket;

// import java.io.IOException;
// import java.util.List;
// import java.util.concurrent.CopyOnWriteArrayList;
// import org.springframework.stereotype.Service;
// import org.springframework.web.socket.TextMessage;
// import org.springframework.web.socket.WebSocketSession;

// @Service
// public class DbUpdateCheckService {
//     private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

//     public void addSession(WebSocketSession session) {
//         sessions.add(session);
//     }

//     public void removeSession(WebSocketSession session) {
//         sessions.remove(session);
//     }

//     public void sendCheckStatus(int status) throws IOException {
//         TextMessage message = new TextMessage(String.valueOf(status));
//         for (WebSocketSession session : sessions) {
//             if (session.isOpen()) {
//                 session.sendMessage(message);
//             }
//         }
//     }
// }
