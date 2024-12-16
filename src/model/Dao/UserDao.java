package model.Dao;

import model.Dto.UserDto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    // 싱글톤
    private static UserDao userDao = new UserDao();
    // JDBC 인터페이스 ,  import java.sql.
    private Connection conn ; // 연동된 결과의 연동 객체를 조작할 인터페이스

    private UserDao(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project01","root", "1234");
            System.out.println("[ project01 Connection OK ]");
        }catch ( ClassNotFoundException e ){
            e.getMessage(); System.out.println("[ project01 Connection fail ]");
            // 실패이유 : 1. 프로젝트내 JDBC 라이브러리 등록 2. 오타(클래스경로,DB서버경로) 체크 3. MYSQL 워크벤치에서 DB 존재 체크
        }catch( SQLException e ){
            e.getMessage(); System.out.println("[ project01 Connection fail ]");
        }
    } // 생성자 end

    // 필요한거
    public static UserDao getInstance(){
        return userDao;
    }

    // 회원가입 예정
    public boolean signUp( UserDto userDto ) {
        try {
            String sql = "insert into Users( user_id, username, password, user_email, user_phone ) values ( ?, ?, ? , ?, ? ) ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userDto.getUserId()); // 1:SQL내 첫번째 ? 뜻한다  : 첫번째 ? 에 입력받은 게시물 내용 을 대입한다.
            ps.setString(2, userDto.getUsername()); // 2:SQL내 두번째 ? 뜻한다 : 두번째 ? 에 입력받은 게시물 작성자를 대입한다.
            ps.setString(3, userDto.getPassword()); // 3:SQL내 세번째 ? 뜻한다 : 세번째 ? 에 입력받은 게시물 비밀번호를 대입한다.
            ps.setString(4, userDto.getUserEmail()); // 3:SQL내 세번째 ? 뜻한다 : 세번째 ? 에 입력받은 게시물 비밀번호를 대입한다.
            ps.setString(5, userDto.getUserPhone()); // 3:SQL내 세번째 ? 뜻한다 : 세번째 ? 에 입력받은 게시물 비밀번호를 대입한다.
            // 4. 기재된 SQL 를 실행한다. execute:실행하다 , Update:최신화하다 , => sql 실행후 최신화한다.
            ps.executeUpdate();
            // 5. 성공했을때 true 반환
            return true;
        }catch ( SQLException e ){
            e.getMessage(); System.out.println("[ 회원가입 등록시 예외발생]");
        }
        // 5. 실패 또는 오류 발생시 false 반환
        return false;
    }

    public UserDto login(UserDto userDto) {
        UserDto loggedInUser = null;
        try {
            String sql = "SELECT * FROM Users WHERE user_id = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userDto.getUserId());
            ps.setString(2, userDto.getPassword());
            ResultSet rs = ps.executeQuery();

            // ResultSet에서 데이터가 존재하면 로그인 성공
            if (rs.next()) {
                loggedInUser = new UserDto(
                        rs.getString("username"),
                        rs.getString("user_id"),
                        rs.getString("password"),
                        rs.getString("user_email"),
                        rs.getString("user_phone")
                );
            }
        } catch (SQLException e) {
            System.out.println("[ 로그인 시 예외 발생 ] " + e.getMessage());
        }
        return loggedInUser;
    }
}