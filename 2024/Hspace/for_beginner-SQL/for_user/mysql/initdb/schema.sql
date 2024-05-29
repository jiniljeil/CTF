USE hspace;

CREATE TABLE user (
	id serial primary key,
	userid varchar(24) not null unique,
	password varchar(50) not null
);

INSERT INTO user (userid, password) VALUES ('admin', '[REDACTED]');