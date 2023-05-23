// package com.example.image_chat_system_api.websocket;

// import org.springframework.web.socket.TextMessage;
// import org.springframework.web.socket.WebSocketSession;
// import org.springframework.web.socket.handler.TextWebSocketHandler;

// public class SocketHandler extends TextWebSocketHandler {
    
//     @Override
//     public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//         // クライアントからのメッセージを受信した際の処理を記述します
//         String receivedMessage = message.getPayload();
//         System.out.println("Received message: " + receivedMessage);
        
//         // メッセージを加工してクライアントに送信する場合は以下のようにします
//         String modifiedMessage = "Modified: " + receivedMessage;
//         session.sendMessage(new TextMessage(modifiedMessage));
//     }
// }
