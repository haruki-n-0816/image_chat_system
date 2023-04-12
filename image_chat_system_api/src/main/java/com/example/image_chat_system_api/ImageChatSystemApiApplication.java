package com.example.image_chat_system_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example.image_chat_system_api.repository")
@SpringBootApplication
public class ImageChatSystemApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageChatSystemApiApplication.class, args);
	}

}
