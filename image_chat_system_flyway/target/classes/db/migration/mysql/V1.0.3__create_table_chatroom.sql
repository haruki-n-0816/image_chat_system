CREATE TABLE chat_history (
	id INT NOT NULL AUTO_INCREMENT,
	chat_room_id INT				                         COMMENT '部屋番号',
    chat_poster CHAR(255)                                    COMMENT '投稿者名',
    user_id CHAR(36)                                         COMMENT 'ユーザーID',
    message CHAR(255)                                        COMMENT '文章',
    image_path CHAR(255)                                     COMMENT '画像パス',
    post_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP    COMMENT '投稿時間',
	PRIMARY KEY (id)
);