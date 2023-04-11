CREATE TABLE chat_room_list (
	room_id INT NOT NULL AUTO_INCREMENT    	COMMENT '部屋id',
	room_name CHAR(255)					    COMMENT '名前',
	PRIMARY KEY (room_id)
);

CREATE TABLE user_list (
    id INT NOT NULL AUTO_INCREMENT          COMMENT '一意のid',
    user_id CHAR(10) NOT NULL               COMMENT '英数字のid',
    user_name CHAR(255) NOT NULL            COMMENT 'ユーザーの名前',
    user_mail CHAR(255) NOT NULL            COMMENT 'メールアドレス',
    user_pw CHAR(255) NOT NULL              COMMENT 'パスワード',
    PRIMARY KEY (id)
);