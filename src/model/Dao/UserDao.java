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
            System.out.println("[ BoardDB Connection OK ]");
        }catch ( ClassNotFoundException e ){
            e.getMessage(); System.out.println("[ BoardDB Connection fail ]");
        }catch( SQLException e ){
            e.getMessage(); System.out.println("[ BoardDB Connection fail ]");
        }
    } // 생성자 end

    // 필요한거
    public static UserDao getInstance(){
        return userDao;
    }

    // 회원가입 예정
    public boolean signUp( UserDto userDto ) {
        try {
            String sql = "insert into Users( user_id, username, password, user_email, user_phone ) values ( ?, ?, ?, ?, ? ) ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userDto.getUserId());
            ps.setString(2, userDto.getUsername());
            ps.setString(3, userDto.getPassword());
            ps.setString(4, userDto.getUserEmail());
            ps.setString(5, userDto.getUserPhone());
            ps.executeUpdate();
            return true;
        }catch ( SQLException e ){
            e.getMessage(); System.out.println("[ 게시물 등록시 예외발생]");
        }
        return false;
    }

    // 로그인 예정
    public boolean login( UserDto userDto ) {
        try {
        String sql = "select * from user";  // 1. SQL 작성
        PreparedStatement ps = conn.prepareStatement(sql); // 2. SQL 기재
        ResultSet rs = ps.executeQuery();
        while( rs.next() ){
            String userid = rs.getString("userid");
            String pwd = rs.getString("pwd");


            UserDto userDto1 = new UserDto( userid, pwd );

            // 여기 아래부터 고칠거
            list.add( userDto );
        }
    }catch ( SQLException e ){ e.getMessage();  System.out.println("[ 게시물 출력시 예외발생]"); }
    // 9. 구성한 리스트 객체 반환
            return list;
} // m end







} // m end
