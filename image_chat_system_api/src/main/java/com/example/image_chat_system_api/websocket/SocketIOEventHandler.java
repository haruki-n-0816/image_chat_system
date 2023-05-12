package com.example.image_chat_system_api.websocket;

import org.springframework.stereotype.Component;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

@Component
public class SocketIOEventHandler implements ConnectListener, DisconnectListener {

    @Override
    public void onConnect(SocketIOClient client) {
        System.out.println("SocketIO client connected: " + client.getSessionId());
    }

    @Override
    public void onDisconnect(SocketIOClient client) {
        System.out.println("SocketIO client disconnected: " + client.getSessionId());
    }
}
