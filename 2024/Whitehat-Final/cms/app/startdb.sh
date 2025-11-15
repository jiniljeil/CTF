#!/bin/bash
service mariadb start

# MariaDB가 성공적으로 시작될 때까지 대기
until mysqladmin ping > /dev/null 2>&1; do
  echo "Waiting for MariaDB to be available..."
  sleep 2
done

# 데이터베이스 및 사용자 설정
echo "Setting up MySQL database and user..."

mysql -u root -p${MYSQL_ROOT_PASSWORD} <<EOF
-- 루트 비밀번호 설정
ALTER USER 'root'@'localhost' IDENTIFIED BY '${MYSQL_ROOT_PASSWORD}';

-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS ${MYSQL_DATABASE} CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 사용자 생성 및 비밀번호 설정
CREATE USER IF NOT EXISTS '${MYSQL_USER}'@'%' IDENTIFIED BY '${MYSQL_PASSWORD}';

-- 사용자에게 권한 부여
GRANT ALL PRIVILEGES ON ${MYSQL_DATABASE}.* TO '${MYSQL_USER}'@'%';

-- 권한 적용
FLUSH PRIVILEGES;
EOF

mysql -u ${MYSQL_USER} -p${MYSQL_PASSWORD} <<EOF
-- 데이터베이스 설정
USE ${MYSQL_DATABASE};

-- users 테이블 생성
CREATE TABLE IF NOT EXISTS users (
    idx INT AUTO_INCREMENT PRIMARY KEY,
    user VARCHAR(50) NOT NULL,
    pass VARCHAR(255) NOT NULL,
    role INT DEFAULT 1,
    filepath VARCHAR(100) DEFAULT ''
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- boardlist 테이블 생성
CREATE TABLE IF NOT EXISTS boardlist (
    idx INT AUTO_INCREMENT PRIMARY KEY,
    bname VARCHAR(50) NOT NULL,
    role INT NOT NULL,
    count INT
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- boardmain 테이블 생성
CREATE TABLE IF NOT EXISTS boardmain (
    idx INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    owner VARCHAR(50) NOT NULL,
    filepath VARCHAR(100)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- boardlist에 boardmain 추가
INSERT INTO boardlist (bname, role, count) VALUES ('main', 1, 0);

-- boardfree 테이블 생성
CREATE TABLE IF NOT EXISTS boardfree (
    idx INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    owner VARCHAR(50) NOT NULL,
    filepath VARCHAR(100)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- boardlist에 boardfree 추가
INSERT INTO boardlist (bname, role, count) VALUES ('free', 1, 0);

-- boardtest 테이블 생성
CREATE TABLE IF NOT EXISTS boardtest (
    idx INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    owner VARCHAR(50) NOT NULL,
    filepath VARCHAR(100)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- boardlist에 boardtest 추가
INSERT INTO boardlist (bname, role, count) VALUES ('test', 2, 0);

EOF

echo "MySQL database and user setup completed."