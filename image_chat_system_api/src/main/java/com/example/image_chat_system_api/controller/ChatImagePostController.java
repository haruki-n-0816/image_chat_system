package com.example.image_chat_system_api.controller;
    
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.image_chat_system_api.service.ChatImagePostService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ChatImagePostController {

    @Autowired
    ChatImagePostService chatImagePostService;

    @PostMapping("/chatImagePost")
    public boolean chatImagePost(@RequestParam("chatRoomId") int chatRoomId,
                                @RequestParam("chatPoster") String chatPoster,
                                @RequestParam("userId") String userId,
                                @RequestParam("image") MultipartFile image) throws IOException {
                                    
        // String relativePath = "directory/file.txt";
        // String saveDirectory = "../../../../../../../../image_chat_system_web/src/imageDownload";
        String saveDirectory = "/Users/mitsuma/training/image_chat_system/image_chat_system_web/public/chatupload";//自分のものに変更
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd.HHmmss");
        String dateTimeString = now.format(formatter);

        String fileName = dateTimeString + "." +userId+ ".png";
        try {
            Path filePath = Paths.get(saveDirectory, fileName);
            Files.write(filePath,image.getBytes());
            String imagePath = fileName;
            boolean result = chatImagePostService.chatImagePost(chatRoomId, chatPoster, userId, imagePath);
            System.out.println(imagePath);

            return result;
        } catch (IOException e) {
            e.printStackTrace(); 
            return false;
        }
    }
}