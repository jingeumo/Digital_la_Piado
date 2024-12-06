drop database if exists project01;   -- 만일 데이터베이스 존재하면 삭제
create database project01;
use project01;

-- Users 테이블 생성
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,  -- 사용자 고유 ID
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
    music_play_status INT DEFAULT 0 ,                     -- 음악 재생 상태
    music_content VARCHAR(255),                        -- 곡설명
    music_price INT,                            -- 음악 가격
    FOREIGN KEY (music_artist_id) REFERENCES users(user_id)  -- 아티스트 ID에 대한 외래키 제약조건
);

-- PlayHistory 테이블 생성
CREATE TABLE PlayHistory (
    play_id INT AUTO_INCREMENT PRIMARY KEY,     -- 플레이 기록 고유 ID
    played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 음악 재생 시간
    user_id INT NOT NULL,                       -- 사용자 ID (Users 테이블의 외래키)
    music_id INT NOT NULL,                      -- 음악 ID (Music 테이블의 외래키)
    FOREIGN KEY (user_id) REFERENCES users(user_id),  -- 사용자 ID에 대한 외래키 제약조건
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
    user_id INT NOT NULL,                       -- 게시판 작성자 ID (Users 테이블의 외래키)
    FOREIGN KEY (user_id) REFERENCES users(user_id)  -- 사용자 ID에 대한 외래키 제약조건
);

-- Purchase 테이블 생성
CREATE TABLE Purchase (
    purchase_id INT AUTO_INCREMENT PRIMARY KEY,  -- 구매 고유 ID
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 구매 날짜
    purchase_price INT NOT NULL,                 -- 구매 가격
    purchase_status INT DEFAULT 1,               -- 구매 상태 (0: 취소, 1: 완료, 기본값 1)
	user_id INT NOT NULL,                        -- 사용자 ID (Users 테이블의 외래키)
    music_id INT NOT NULL,                       -- 음악 ID (Music 테이블의 외래키)
    FOREIGN KEY (user_id) REFERENCES users(user_id),  -- 사용자 ID에 대한 외래키 제약조건
    FOREIGN KEY (music_id) REFERENCES Music(music_id)  -- 음악 ID에 대한 외래키 제약조건
);

-- ArtistMusic 테이블 생성
CREATE TABLE ArtistMusic (
    artist_music_id INT AUTO_INCREMENT PRIMARY KEY,  -- 아티스트 음악 등록 고유 ID
    artist_music_registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 음악 등록 날짜
    artist_id INT NOT NULL,                           -- 아티스트 ID (Users 테이블의 외래키)
    music_id INT NOT NULL,                           -- 음악 ID (Music 테이블의 외래키)
    FOREIGN KEY (artist_id) REFERENCES users(user_id),  -- 아티스트 ID에 대한 외래키 제약조건
    FOREIGN KEY (music_id) REFERENCES Music(music_id)   -- 음악 ID에 대한 외래키 제약조건
);

INSERT INTO users (username, password, user_email, user_phone, user_gender, user_grade, user_status, user_music) VALUES ('john_doe', 'hashed_password_1', 'john@example.com', '010-1234-5678', 0, 1, 1, 'playlist1');
INSERT INTO users (username, password, user_email, user_phone, user_gender, user_grade, user_status, user_music) VALUES ('jane_smith', 'hashed_password_2', 'jane@example.com', '010-2345-6789', 1, 2, 1, 'playlist2');
INSERT INTO users (username, password, user_email, user_phone, user_gender, user_grade, user_status, user_music) VALUES ('alice_wonder', 'hashed_password_3', 'alice@example.com', '010-3456-7890', 1, 1, 1, 'playlist3');
INSERT INTO users (username, password, user_email, user_phone, user_gender, user_grade, user_status, user_music) VALUES ('bob_builder', 'hashed_password_4', 'bob@example.com', '010-4567-8901', 0, 3, 1, 'playlist4');
INSERT INTO users (username, password, user_email, user_phone, user_gender, user_grade, user_status, user_music) VALUES ('charlie_brown', 'hashed_password_5', 'charlie@example.com', '010-5678-9012', 0, 1, 1, 'playlist5');

INSERT INTO Music (music_title, music_artist_id, music_genre, music_release_date, music_file_path) VALUES ('Song of the Ocean', 1, 'Pop', '2023-01-15', '/music/song_of_the_ocean.mp3');
INSERT INTO Music (music_title, music_artist_id, music_genre, music_release_date, music_file_path) VALUES ('Chasing Stars', 2, 'Rock', '2023-02-20', '/music/chasing_stars.mp3');
INSERT INTO Music (music_title, music_artist_id, music_genre, music_release_date, music_file_path) VALUES ('Whispers in the Wind', 3, 'Jazz', '2023-03-10', '/music/whispers_in_the_wind.mp3');
INSERT INTO Music (music_title, music_artist_id, music_genre, music_release_date, music_file_path) VALUES ('Dance with Me', 4, 'EDM', '2023-04-05', '/music/dance_with_me.mp3');
INSERT INTO Music (music_title, music_artist_id, music_genre, music_release_date, music_file_path) VALUES ('Echoes of Time', 5, 'Classical', '2023-05-25', '/music/echoes_of_time.mp3');
