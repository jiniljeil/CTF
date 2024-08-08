USE cce;

CREATE TABLE user (
	id serial primary key,
	userid varchar(24) not null unique,
	password varchar(100) not null
);

INSERT INTO user (userid, password) VALUES ('61646d696e', '737570657253656372657441646d696e50617373776f72642140233132');