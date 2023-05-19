// package com.example.image_chat_system_api.websocket;

// import org.springframework.web.socket.TextMessage;
// import org.springframework.web.socket.WebSocketSession;
// import org.springframework.web.socket.handler.TextWebSocketHandler;

// public class SocketHandler {

//     @Override
//     protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//         // クライアントからのメッセージを受け取った時の処理を記述します
//         String clientMessage = message.getPayload();
//         System.out.println("Received message from client: " + clientMessage);
        
//         // レスポンスとしてメッセージを送信します
//         String serverMessage = "Hello from server!";
//         session.sendMessage(new TextMessage(serverMessage));
//     }
// }
