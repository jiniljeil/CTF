CREATE DATABASE ccend;

USE ccend;

CREATE TABLE events (name VARCHAR(32), writer VARCHAR(64), pw VARCHAR(68), body TEXT);
INSERT INTO events (name, writer, pw, body) VALUES
    ('Sample event', '359***', '296***', 'this is sample event'),
    ('Flag', '368***', '6db***', 'cce2024{***}'),
    ('A chicken', '3a7***', 'f81***', 'a chicken')