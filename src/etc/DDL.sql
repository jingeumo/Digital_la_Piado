# 1. 게시판 만들기 위한 데이터베이스DB 작성
create database boards;
# 2. 게시판 만들기 위한 데이터베이스 "boards" 이용
CREATE TABLE boards (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    author VARCHAR(100) NOT NULL
);

select * from boards;