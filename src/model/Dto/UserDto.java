package model.Dto;

import java.sql.Timestamp;

public class UserDto {
    private String userId;                // 사용자 고유 ID
    private String username;            // 사용자 이름 (고유)
    private String password;            // 비밀번호 (암호화된 형태로 저장)
    private String userEmail;           // 이메일 주소 (고유)
    private Timestamp userCreatedAt;    // 계정 생성 시간
    private String userPhone;           // 전화번호 (문자열로 저장)
    private int userGender;             // 0: 남자, 1: 여자
    private int userGrade;              // 사용자 역할 등급: 1: 일반회원, 2: 아티스트 회원, 3: 관리자
    private Timestamp userUpdatedAt;    // 계정 정보 수정 시간
    private int userStatus;             // 0: 로그아웃 상태, 1: 로그인 상태
    private String userMusic;           // 유저 플레이리스트

    // 기본 생성자
    public UserDto() {
    }

    public UserDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    // 모든 필드를 포함하는 생성자
    public UserDto(String userId, String username, String password, String userEmail,
                   Timestamp userCreatedAt, String userPhone, int userGender,
                   int userGrade, Timestamp userUpdatedAt, int userStatus,
                   String userMusic) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userEmail = userEmail;
        this.userCreatedAt = userCreatedAt;
        this.userPhone = userPhone;
        this.userGender = userGender;
        this.userGrade = userGrade;
        this.userUpdatedAt = userUpdatedAt;
        this.userStatus = userStatus;
        this.userMusic = userMusic;
    }

    public UserDto(String username, String userId, String password, String userEmail, String userPhone) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    public UserDto(String userid, String password) {
        this.userId = userId;
        this.password = password;
    }

    // Getter 및 Setter 메서드
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Timestamp getUserCreatedAt() {
        return userCreatedAt;
    }

    public void setUserCreatedAt(Timestamp userCreatedAt) {
        this.userCreatedAt = userCreatedAt;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getUserGender() {
        return userGender;
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    public int getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(int userGrade) {
        this.userGrade = userGrade;
    }

    public Timestamp getUserUpdatedAt() {
        return userUpdatedAt;
    }

    public void setUserUpdatedAt(Timestamp userUpdatedAt) {
        this.userUpdatedAt = userUpdatedAt;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserMusic() {
        return userMusic;
    }

    public void setUserMusic(String userMusic) {
        this.userMusic = userMusic;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userCreatedAt=" + userCreatedAt +
                ", userPhone='" + userPhone + '\'' +
                ", userGender=" + userGender +
                ", userGrade=" + userGrade +
                ", userUpdatedAt=" + userUpdatedAt +
                ", userStatus=" + userStatus +
                ", userMusic='" + userMusic + '\'' +
                '}';
    }
}