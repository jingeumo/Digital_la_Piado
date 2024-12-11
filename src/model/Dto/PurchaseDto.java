package model.Dto;

import java.time.LocalDateTime;

public class PurchaseDto {
    private int purchaseId;          // 구매 고유 ID
    private LocalDateTime purchaseDate; // 구매 날짜
    private int purchasePrice;       // 구매 가격
    private int purchaseStatus;      // 구매 상태 (0: 취소, 1: 완료)
    private int userId;              // 사용자 ID
    private int musicId;             // 음악 ID



    public PurchaseDto(int purchaseId, LocalDateTime purchaseDate, int purchasePrice, int purchaseStatus, int userId, int musicId) {
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.purchaseStatus = purchaseStatus;
        this.userId = userId;
        this.musicId = musicId;
    }

    public int getPurchaseId() {
        return purchaseId;
    }
    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }
    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    public int getPurchasePrice() {
        return purchasePrice;
    }
    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public int getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(int purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }
    @Override
    public String toString() {
        return "PurchaseDto{" +
                "purchaseId=" + purchaseId +
                ", purchaseDate=" + purchaseDate +
                ", purchasePrice=" + purchasePrice +
                ", purchaseStatus=" + purchaseStatus +
                ", userId=" + userId +
                ", musicId=" + musicId +
                '}';
    }
}
