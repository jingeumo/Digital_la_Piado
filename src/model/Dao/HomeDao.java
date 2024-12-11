package model.Dao;

import model.Dto.MusicDto;

import java.sql.*;
import java.util.ArrayList;

public class HomeDao {
    private Connection conn;
    private static HomeDao homeDao = new HomeDao();
    private HomeDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project01", "root", "1234");
            System.out.println("[ project01DB Connection OK ]");
        } catch (ClassNotFoundException e){
            e.getMessage(); System.out.println("[ JDBC Driver not found. ]");
        }catch (SQLException e) {
            e.getMessage(); System.out.println("[ project01DB Connection fail ]");
        }
    }
    public static HomeDao getInstance(){return getInstance();}

    // 인기 음악을 가져오는 메서드
    public ArrayList<MusicDto> getPopularMusic() {
        ArrayList<MusicDto> popularMusicList = new ArrayList<>();
        try{
            String sql = "select * from Music order by music_id asc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //순위     제목    아티스트    재생  	곡정보(...) 저장   구매        날짜
                int music_id = rs.getInt("music_id");
                String music_title = rs.getString("music_title");
                int music_artist_id = rs.getInt("music_artist_id");
                int music_play_status = rs.getInt("music_play_status");
                String music_content = rs.getString("music_content");
                int music_purchase_status = rs.getInt("music_purchase_status");
                Date music_release_date = rs.getDate("music_release_date");
                MusicDto musicDto = new MusicDto(music_id, music_title, music_artist_id,
                        music_play_status, music_content, music_purchase_status, music_release_date);
            }
        }catch (SQLException e) {
            e.getMessage();
            System.out.println("[ 인기 많은 순으로 출력 시 예외 발생 ]");
        }
        return popularMusicList;
    }

    // 최근 등록된 음악을 가져오는 메서드
    public ArrayList<MusicDto> getRecentMusic() {
        ArrayList<MusicDto> recentMusicList = new ArrayList<>();
        // 데이터베이스 쿼리 실행하여 recentMusicList에 음악 정보를 추가
        return recentMusicList;
    }
}
