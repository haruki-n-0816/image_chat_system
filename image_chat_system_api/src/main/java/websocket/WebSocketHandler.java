package websocket;
import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // クライアントからのメッセージを取得
        String receivedMessage = message.getPayload();
        System.out.println("Received message: " + receivedMessage);

        // クライアントに応答を送信
        String responseMessage = "Received message: " + receivedMessage;
        session.sendMessage(new TextMessage(responseMessage));
    }
}