package model.Dto;

import java.sql.Timestamp;

public class PlaylistDto {
    private int playId;          // 플레이 기록 고유 ID
    private Timestamp playedAt;  // 음악 재생 시간
    private int userNum;         // 사용자 ID
    private int musicId;         // 음악 ID

    public PlaylistDto(int playId, Timestamp playedAt, int userNum, MusicDto musicId) {
        this.playId = playId;
        this.playedAt = playedAt;
        this.userNum = userNum;
    }

    public int getPlayId() {
        return playId;
    }

    public void setPlayId(int playId) {
        this.playId = playId;
    }

    public Timestamp getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(Timestamp playedAt) {
        this.playedAt = playedAt;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    @Override
    public String toString() {
        return "PlaylistDto{" +
                "playId=" + playId +
                ", playedAt=" + playedAt +
                ", userNum=" + userNum +
                ", musicId=" + musicId +
                '}';
    }
}
