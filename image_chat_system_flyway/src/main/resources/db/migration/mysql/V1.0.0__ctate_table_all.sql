CREATE TABLE chat_room_list (
	room_id INT NOT NULL AUTO_INCREMENT    	COMMENT 'id',
	room_name CHAR(255)					COMMENT '名前',
	PRIMARY KEY (room_id)
);

CREATE TABLE user_list (
    id INT NOT NULL AUTO_INCREMENT,
    user_id CHAR(10) NOT NULL,
    user_name CHAR(255) NOT NULL,
    user_mail CHAR(255) NOT NULL,
    user_pw CHAR(255) NOT NULL,
    PRIMARY KEY (id)
);