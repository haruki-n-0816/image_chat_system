package com.example.image_chat_system_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@SpringBootApplication
public class ImageChatSystemApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageChatSystemApiApplication.class, args);
		startSocketServer();
    }

    public static void startSocketServer() {
        try {
            // ソケットサーバーの作成
            final Socket socket = IO.socket("http://localhost:8080"); // クライアントのURLに合わせて変更してください

            // クライアントからのメッセージ受信時の処理
            socket.on("message", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String message = (String) args[0];
                    System.out.println("Received message: " + message);

                    // クライアントにメッセージを送信
                    socket.emit("message", "Server received your message: " + message);
                }
            });

            // ソケットサーバーの開始
            socket.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
	}
}
