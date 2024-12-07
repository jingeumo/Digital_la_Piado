package model.Dto;

import java.sql.Date;
import java.sql.Timestamp;

public class MusicDto {
    private int musicId;                // 음악 고유 ID
    private String musicTitle;          // 음악 제목
    private int musicArtistId;          // 아티스트 ID
    private String musicGenre;          // 음악 장르
    private Date musicReleaseDate; // 발매일
    private String musicFilePath;       // 음악 파일 경로
    private Timestamp musicCreatedAt;   // 음악 등록 시간
    private Timestamp musicUpdatedAt;   // 음악 정보 수정 시간
    private int musicView;              // 조회수
    private int musicStatus;            // 음악 상태 (0: 비공개, 1: 공개)
    private int musicPlayStatus;        // 음악 재생 상태
    private String musicContent;         // 곡설명
    private int musicPrice;             // 음악 가격

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public int getMusicArtistId() {
        return musicArtistId;
    }

    public void setMusicArtistId(int musicArtistId) {
        this.musicArtistId = musicArtistId;
    }

    public String getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    public Date getMusicReleaseDate() {
        return musicReleaseDate;
    }

    public void setMusicReleaseDate(Date musicReleaseDate) {
        this.musicReleaseDate = musicReleaseDate;
    }

    public String getMusicFilePath() {
        return musicFilePath;
    }

    public void setMusicFilePath(String musicFilePath) {
        this.musicFilePath = musicFilePath;
    }

    public Timestamp getMusicCreatedAt() {
        return musicCreatedAt;
    }

    public void setMusicCreatedAt(Timestamp musicCreatedAt) {
        this.musicCreatedAt = musicCreatedAt;
    }

    public Timestamp getMusicUpdatedAt() {
        return musicUpdatedAt;
    }

    public void setMusicUpdatedAt(Timestamp musicUpdatedAt) {
        this.musicUpdatedAt = musicUpdatedAt;
    }

    public int getMusicView() {
        return musicView;
    }

    public void setMusicView(int musicView) {
        this.musicView = musicView;
    }

    public int getMusicStatus() {
        return musicStatus;
    }

    public void setMusicStatus(int musicStatus) {
        this.musicStatus = musicStatus;
    }

    public int getMusicPlayStatus() {
        return musicPlayStatus;
    }

    public void setMusicPlayStatus(int musicPlayStatus) {
        this.musicPlayStatus = musicPlayStatus;
    }

    public String getMusicContent() {
        return musicContent;
    }

    public void setMusicContent(String musicContent) {
        this.musicContent = musicContent;
    }

    public int getMusicPrice() {
        return musicPrice;
    }

    public void setMusicPrice(int musicPrice) {
        this.musicPrice = musicPrice;
    }

    public MusicDto(int musicId, String musicTitle, int musicArtistId,
                    String musicGenre, Date musicReleaseDate,
                    String musicFilePath, Timestamp musicCreatedAt, Timestamp musicUpdatedAt, int musicView, int musicStatus,
                    int musicPlayStatus, String musicContent, int musicPrice) {
        this.musicId = musicId;
        this.musicTitle = musicTitle;
        this.musicArtistId = musicArtistId;
        this.musicGenre = musicGenre;
        this.musicReleaseDate = musicReleaseDate;
        this.musicFilePath = musicFilePath;
        this.musicCreatedAt = musicCreatedAt;
        this.musicUpdatedAt = musicUpdatedAt;
        this.musicView = musicView;
        this.musicStatus = musicStatus;
        this.musicPlayStatus = musicPlayStatus;
        this.musicContent = musicContent;
        this.musicPrice = musicPrice;
    }

    @Override
    public String toString() {
        return "MusicDto{" +
                "musicId=" + musicId +
                ", musicTitle='" + musicTitle + '\'' +
                ", musicArtistId=" + musicArtistId +
                ", musicGenre='" + musicGenre + '\'' +
                ", musicReleaseDate=" + musicReleaseDate +
                ", musicFilePath='" + musicFilePath + '\'' +
                ", musicCreatedAt=" + musicCreatedAt +
                ", musicUpdatedAt=" + musicUpdatedAt +
                ", musicView=" + musicView +
                ", musicStatus=" + musicStatus +
                ", musicPlayStatus=" + musicPlayStatus +
                ", musicContent='" + musicContent + '\'' +
                ", musicPrice=" + musicPrice +
                '}';
    }
}
