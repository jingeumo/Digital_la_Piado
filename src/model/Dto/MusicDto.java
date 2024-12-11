package model.Dto;

import java.sql.Date;

public class MusicDto {
    private int music_id;                   // 음악 고유 ID
    private String music_title;             // 음악 제목
    private int music_artist_id;            // 아티스트 ID (Users 테이블의 외래키)
    private String music_genre;             // 음악 장르
    private Date music_release_date;        // 발매일
    private String music_file_path;         // 음악 파일 경로
    private Date music_created_at;          // 음악 등록 시간
    private Date music_updated_at;          // 음악 정보 수정 시간
    private int music_view;                 // 조회수 (기본값 0)
    private int music_status;               // 음악 상태 (0 : 비공개, 1: 공개, 기본값 1)
    private int music_price;                // 음악 가격
    private int music_play_status;          // 음악 재생 상태
    private String music_content;           // 음악 설명
    private int music_purchase_status;      // 음악 구매 상태
    private int music_save_status;      // 음악 저장 상태

    public MusicDto(int music_id, int music_artist_id, String music_title, int music_play_status, String music_content, int music_purchase_status, Date music_release_date) {
        this.music_id = music_id;
        this.music_artist_id = music_artist_id;
        this.music_title = music_title;
        this.music_play_status = music_play_status;
        this.music_content = music_content;
        this.music_purchase_status = music_purchase_status;
        this.music_release_date = music_release_date;
    }

    public int getMusic_play_status() {return music_play_status;}
    public void setMusic_play_status(int music_play_status) {this.music_play_status = music_play_status;}
    public int getMusic_id() {return music_id;}
    public void setMusic_id(int music_id) {this.music_id = music_id;}
    public String getMusic_title() {return music_title;}
    public void setMusic_title(String music_title) {this.music_title = music_title;}
    public int getMusic_artist_id() {return music_artist_id;}
    public void setMusic_artist_id(int music_artist_id) {this.music_artist_id = music_artist_id;}
    public String getMusic_genre() {return music_genre;}
    public void setMusic_genre(String music_genre) {this.music_genre = music_genre;}
    public Date getMusic_release_date() {return music_release_date;}
    public void setMusic_release_date(Date music_release_date) {this.music_release_date = music_release_date;}
    public String getMusic_file_path() {return music_file_path;}
    public void setMusic_file_path(String music_file_path) {this.music_file_path = music_file_path;}
    public Date getMusic_created_at() {return music_created_at;}
    public void setMusic_created_at(Date music_created_at) {this.music_created_at = music_created_at;}
    public Date getMusic_updated_at() {return music_updated_at;}
    public void setMusic_updated_at(Date music_updated_at) {this.music_updated_at = music_updated_at;}
    public int getMusic_view() {return music_view;}
    public void setMusic_view(int music_view) {this.music_view = music_view;}
    public int getMusic_status() {return music_status;}
    public void setMusic_status(int music_status) {this.music_status = music_status;}
    public int getMusic_price() {return music_price;}
    public void setMusic_price(int music_price) {this.music_price = music_price;}
    public String getMusic_content() {return music_content;}
    public int getMusic_purchase_status() {return music_purchase_status;}

    public void setMusic_purchase_status(int music_purchase_status) {
        this.music_purchase_status = music_purchase_status;
    }

    public void setMusic_content(String music_content) {this.music_content = music_content;}
    public int getMusic_save_status() {return music_save_status;}
    public void setMusic_save_status(int music_save_status) {this.music_save_status = music_save_status;}

    public MusicDto() {
        this.music_view = 0; // 조회수 기본값
        this.music_status = 1; // 공개 상태 기본값
        this.music_play_status = 0;
        this.music_purchase_status = 0;
        this.music_save_status = 0;
    }

    public MusicDto(int music_id, String music_title, int music_artist_id, String music_genre,
                    Date music_release_date, String music_file_path, Date music_created_at,
                    Date music_updated_at, int music_view, int music_status, int music_price,
                    int music_play_status, String music_content, int music_save_status) {
        this.music_id = music_id;
        this.music_title = music_title;
        this.music_artist_id = music_artist_id;
        this.music_genre = music_genre;
        this.music_release_date = music_release_date;
        this.music_file_path = music_file_path;
        this.music_created_at = music_created_at;
        this.music_updated_at = music_updated_at;
        this.music_view = music_view;
        this.music_status = music_status;
        this.music_price = music_price;
        this.music_play_status = music_play_status;
        this.music_content = music_content;
        this.music_save_status = music_save_status;
    }

    @Override
    public String toString() {
        return "MusicDto{" +
                "music_id=" + music_id +
                ", music_title='" + music_title + '\'' +
                ", music_artist_id=" + music_artist_id +
                ", music_genre='" + music_genre + '\'' +
                ", music_release_date=" + music_release_date +
                ", music_file_path='" + music_file_path + '\'' +
                ", music_created_at=" + music_created_at +
                ", music_updated_at=" + music_updated_at +
                ", music_view=" + music_view +
                ", music_status=" + music_status +
                ", music_price=" + music_price +
                ", music_play_status=" + music_play_status +
                ", music_content='" + music_content + '\'' +
                ", music_purchase_status=" + music_purchase_status +
                ", music_save_status=" + music_save_status +
                '}';
    }
}
