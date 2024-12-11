drop database if exists project01;   -- 만일 데이터베이스 존재하면 삭제
create database project01;
use project01;

-- 테이블 생성 ---------------------------------------------------------------------------------------------------
-- Users 테이블 생성
CREATE TABLE Users (
    user_num INT AUTO_INCREMENT PRIMARY KEY,  -- 사용자 고유 ID
    user_id  VARCHAR(255) NOT NULL,				-- 사용자 아이디
    username VARCHAR(50) NOT NULL UNIQUE,     -- 사용자 이름 (고유)
    password VARCHAR(255) NOT NULL,           -- 비밀번호 (암호화된 형태로 저장)
    user_email VARCHAR(100) NOT NULL UNIQUE,  -- 이메일 주소 (고유)
    user_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 계정 생성 시간
    user_phone VARCHAR(15),                    -- 전화번호 (문자열로 저장)
    user_gender INT,                           -- 0: 남자, 1: 여자
    user_grade INT,                            -- 사용자 역할 등급: 1: 일반회원, 2: 아티스트 회원, 3: 관리자
    user_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 계정 정보 수정 시간
    user_status INT,                           -- 0: 로그아웃 상태, 1: 로그인 상태
    user_music VARCHAR(10)                     -- 유저 플레이리스트
);

-- Music 테이블 생성
CREATE TABLE Music (
    music_id INT AUTO_INCREMENT PRIMARY KEY,   -- 음악 고유 ID
    music_title VARCHAR(100) NOT NULL,         -- 음악 제목
    music_artist_id INT NOT NULL,               -- 아티스트 ID (Users 테이블의 외래키)
    music_genre VARCHAR(50),                    -- 음악 장르
    music_release_date DATE,                    -- 발매일
    music_file_path VARCHAR(255) NOT NULL,     -- 음악 파일 경로
    music_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 음악 등록 시간
    music_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 음악 정보 수정 시간
    music_view INT DEFAULT 0,                   -- 조회수 (기본값 0)
    music_status INT DEFAULT 1,                 -- 음악 상태 (0 : 비공개, 1: 공개, 기본값 1)
    music_price INT,                            -- 음악 가격
    music_play_status int default 0,            -- 음악 재생 상태
    purchase_id int,
    FOREIGN KEY (purchase_id) REFERENCES Purchase(purchase_id)
    FOREIGN KEY (music_artist_id) REFERENCES users(user_num)  -- 아티스트 ID에 대한 외래키 제약조건
);

-- PlayHistory 테이블 생성
CREATE TABLE Playlist (
    play_id INT AUTO_INCREMENT PRIMARY KEY,     -- 플레이 기록 고유 ID
    played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 음악 재생 시간
    user_num INT NOT NULL,                       -- 사용자 ID (Users 테이블의 외래키)
    music_id INT NOT NULL,                      -- 음악 ID (Music 테이블의 외래키)
    FOREIGN KEY (user_num) REFERENCES users(user_num),  -- 사용자 ID에 대한 외래키 제약조건
    FOREIGN KEY (music_id) REFERENCES Music(music_id)  -- 음악 ID에 대한 외래키 제약조건
);

-- board 테이블 생성
CREATE TABLE board (
    board_id INT AUTO_INCREMENT PRIMARY KEY,   -- 게시판 고유 ID
    board_title VARCHAR(100) NOT NULL,         -- 게시판 제목
    board_content TEXT NOT NULL,                -- 게시판 내용
    board_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 게시판 등록 시간
    board_view INT DEFAULT 0,                  -- 게시판 조회수 (기본값 0)
    board_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 게시판 수정 시간
    board_status INT DEFAULT 1,                 -- 게시판 상태(0 : 비공개, 1: 공개, 기본값 1)
    user_num INT NOT NULL,                       -- 게시판 작성자 ID (Users 테이블의 외래키)
    FOREIGN KEY (user_num) REFERENCES users(user_num)  -- 사용자 ID에 대한 외래키 제약조건
);

-- Purchase 테이블 생성
CREATE TABLE Purchase (
    purchase_id INT AUTO_INCREMENT PRIMARY KEY,  -- 구매 고유 ID
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 구매 날짜
    purchase_price INT NOT NULL,                 -- 구매 가격
    purchase_status INT DEFAULT 1,               -- 구매 상태 (0: 취소, 1: 완료, 기본값 1)
	user_num INT NOT NULL,                        -- 사용자 ID (Users 테이블의 외래키)
    music_id INT NOT NULL,                       -- 음악 ID (Music 테이블의 외래키)
    FOREIGN KEY (user_num) REFERENCES users(user_num),  -- 사용자 ID에 대한 외래키 제약조건
    FOREIGN KEY (music_id) REFERENCES Music(music_id)  -- 음악 ID에 대한 외래키 제약조건
);

-- ArtistMusic 테이블 생성
CREATE TABLE ArtistMusic (
    artist_music_id INT AUTO_INCREMENT PRIMARY KEY,  -- 아티스트 음악 등록 고유 ID
    artist_music_registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 음악 등록 날짜
    artist_id INT NOT NULL,                           -- 아티스트 ID (Users 테이블의 외래키)
    music_id INT NOT NULL,                           -- 음악 ID (Music 테이블의 외래키)
    FOREIGN KEY (artist_id) REFERENCES users(user_num),  -- 아티스트 ID에 대한 외래키 제약조건
    FOREIGN KEY (music_id) REFERENCES Music(music_id)   -- 음악 ID에 대한 외래키 제약조건
);

-- insert 추가 sql문 ---------------------------------------------------------------------------------------------------------------
INSERT INTO Users (user_id, username, password, user_email, user_phone, user_gender, user_grade, user_status, user_music) VALUES
('user1', '홍길동', 'hashed_password1', 'user1@example.com', '010-1234-5678', 0, 1, 1, 'playlist1'),
('user2', '김영희', 'hashed_password2', 'user2@example.com', '010-2345-6789', 1, 1, 1, 'playlist2'),
('user3', '이철수', 'hashed_password3', 'user3@example.com', '010-3456-7890', 0, 2, 1, 'playlist3'),
('user4', '박지민', 'hashed_password4', 'user4@example.com', '010-4567-8901', 1, 3, 1, 'playlist4'),
('user5', '최민수', 'hashed_password5', 'user5@example.com', '010-5678-9012', 0, 1, 1, 'playlist5');

INSERT INTO Music (music_title, music_artist_id, music_genre, music_release_date, music_file_path, music_view) VALUES
('Dreams Come True', 1, 'Pop', '2023-11-01', '/music/dreams_come_true.mp3', FLOOR(RAND() * 1001)),
('Lost in the Stars', 2, 'Rock', '2023-11-15', '/music/lost_in_the_stars.mp3', FLOOR(RAND() * 1004)),
('Night Breeze', 3, 'Jazz', '2023-12-01', '/music/night_breeze.mp3', FLOOR(RAND() * 3024)),
('Heartbeats', 4, 'EDM', '2023-12-10', '/music/heartbeats.mp3', FLOOR(RAND() * 2000)),
('Whispers of Love', 5, 'Classical', '2023-12-15', '/music/whispers_of_love.mp3', FLOOR(RAND() * 2520));

INSERT INTO Playlist (user_num, music_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(4, 5);

INSERT INTO board (board_title, board_content, board_view, board_status, user_num) VALUES
('게시글1', '이것은 1 번째 게시글입니다.', 0, 1, 1),
('게시글2', '이것은 2 번째 게시글입니다.', 5, 1, 2),
('게시글3', '이것은 3 번째 게시글입니다.', 10, 1, 3),
('게시글4', '이것은 4 번째 게시글입니다.', 3, 1, 4),
('게시글5', '이것은 5 번째 게시글입니다.', 2, 1, 5);

INSERT INTO Purchase (purchase_date, purchase_price, purchase_status, user_num, music_id) VALUES
(CURRENT_TIMESTAMP, 1000, 1, 1, 1),
(CURRENT_TIMESTAMP, 1500, 1, 2, 2),
(CURRENT_TIMESTAMP, 2000, 1, 3, 3),
(CURRENT_TIMESTAMP, 2500, 1, 4, 4),
(CURRENT_TIMESTAMP, 3000, 1, 5, 5);

INSERT INTO ArtistMusic (artist_music_registration_date, artist_id, music_id) VALUES
(CURRENT_TIMESTAMP, 1, 1),
(CURRENT_TIMESTAMP, 2, 2),
(CURRENT_TIMESTAMP, 3, 3),
(CURRENT_TIMESTAMP, 4, 4),
(CURRENT_TIMESTAMP, 5, 5);

-- 테이블 조회 sql문 ----------------------------------------------------------------------------------------------------
select * from Users order by user_id asc;
select * from Music order by music_id asc;
select * from Playlist order by play_id asc;
select * from board order by board_id asc;
select * from Purchase order by purchase_id asc;
SELECT * FROM ArtistMusic ORDER BY artist_music_id asc;

select * from ArtistMusic;

