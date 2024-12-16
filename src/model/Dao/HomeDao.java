package model.Dao;

import model.Dto.MusicDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public static HomeDao getInstance(){return homeDao;}

    // 인기 음악을 가져오는 메서드
    public ArrayList< Map<String , String> > getPopularMusic() {
        ArrayList< Map<String , String> > popularMusicList = new ArrayList<>();
        try{
            String sql = "SELECT @rownum := @rownum + 1 AS 순위,m.music_title AS 제목,u.username AS 아티스트,\n" +
                    "CASE WHEN ph.played_at IS NOT NULL THEN '완료' ELSE '대기' END AS 재생,'곡설명' AS 곡정보, m.music_price AS 가격,\n" +
                    "CASE WHEN p.purchase_status = 1 THEN 'O' ELSE 'X' END AS 구매상태,m.music_release_date AS 발매일\n" +
                    "FROM Music m\n" +
                    "JOIN Users u ON m.music_artist_id = u.user_num\n" +
                    "LEFT JOIN Playlist ph ON m.music_id = ph.music_id AND ph.user_num = 1\n" +
                    "LEFT JOIN Purchase p ON m.music_id = p.music_id AND p.user_num = 1\n" +
                    "JOIN (SELECT @rownum := 0) r\n" +
                    "WHERE m.music_status = 1\n" +
                    "ORDER BY m.music_view DESC;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //순위     제목    아티스트    재생  	곡정보(...) 저장   구매        날짜
                String music_id = rs.getString(1);
                String music_title = rs.getString(2);
                String music_artist_id = rs.getString(3);
                String music_play_status = rs.getString(4);
                String music_content = rs.getString(5);
//                int music_save_status = rs.getInt(6);
                String music_purchase_status = rs.getString(7);
                String music_price = rs.getString(6);
                String music_release_date = rs.getString(8);

                Map<String , String> map = new HashMap<>();
                map.put( "music_id" , music_id );
                map.put( "music_title" , music_title );
                map.put( "music_artist_id" , music_artist_id );
                map.put( "music_play_status" , music_play_status );
                map.put( "music_content" , music_content );
                map.put( "music_purchase_status" , music_purchase_status );
                map.put( "music_price =" , music_price  );
                map.put( "music_release_date" , music_release_date );


                popularMusicList.add( map);

            }
        }catch (SQLException e) {
            e.getMessage();
            System.out.println("[ 인기 많은 순으로 출력 시 예외 발생 ]"+e);
        }
        return popularMusicList;
    }

    // 최근 등록된 음악을 가져오는 메서드
    public ArrayList<MusicDto> getRecentMusic() {
        ArrayList<MusicDto> recentMusicList = new ArrayList<>();
        try{
            String sql = "SELECT @rownum := @rownum + 1 AS 순위,m.music_title AS 제목,u.username AS 아티스트,\n" +
                    "CASE WHEN ph.played_at IS NOT NULL THEN '완료' ELSE '대기' END AS 재생,'곡설명' AS 곡정보, m.music_price AS 가격,\n" +
                    "CASE WHEN p.purchase_status = 1 THEN 'O' ELSE 'X' END AS 구매상태, m.music_release_date AS 발매일\n" +
                    "FROM Music m\n" +
                    "JOIN Users u ON m.music_artist_id = u.user_num\n" +
                    "LEFT JOIN Playlist ph ON m.music_id = ph.music_id AND ph.user_num = ? \n" +
                    "LEFT JOIN Purchase p ON m.music_id = p.music_id AND p.user_num = ? \n" +
                    "JOIN (SELECT @rownum := 0) r \n" +
                    "WHERE m.music_status = 1 \n" +
                    "ORDER BY m.music_release_date DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //순위     제목    아티스트    재생  	곡정보(...) 저장   구매        날짜
                int music_id = rs.getInt("music_id");
                String music_title = rs.getString("music_title");
                int music_artist_id = rs.getInt("music_artist_id");
                int music_play_status = rs.getInt("music_play_status");
                String music_content = rs.getString("music_content");
                int music_save_status = rs.getInt("music_save_status");
                int music_purchase_status = rs.getInt("music_purchase_status");
                int music_price = rs.getInt("music_price");
                Date music_release_date = rs.getDate("music_release_date");
                MusicDto musicDto = new MusicDto(music_id, music_title, music_artist_id,
                        music_play_status, music_content, music_save_status, music_price,
                        music_purchase_status, music_release_date);
                recentMusicList.add(musicDto);
            }
        }catch (SQLException e) {
            e.getMessage();
            System.out.println("[ 인기 많은 순으로 출력 시 예외 발생 ]");
        }
        return recentMusicList;
    }
    // 구매하기
    public boolean updatePurchaseStatus(MusicDto music) {
        try{
            String sql = "UPDATE Purchase SET purchase_status = 1, purchase_date = CURRENT_TIMESTAMP,  purchase_price = 1\n" +
                    "WHERE user_num = ?  AND music_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int result = ps.executeUpdate();
            if (result == 1) {
                return true;    // 구매 성공
            }
        }catch(SQLException e) {
            e.getMessage();
            System.out.println("[ 구매 시 예외 발생 ]");
        }
        return false;
    }

    // 사용자 이름으로 ID 가져오기
    private int getUserIdByUsername(String username) {
        String sql = "SELECT user_num FROM users WHERE username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_num");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 사용자 ID가 없을 경우
    }


    // playlist로 저장
    public boolean saveMusicToPlaylist(MusicDto music, String user_id) {
        try {
            String sql = "INSERT INTO Playlist (user_num, music_id) VALUES (?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            // 값 설정
            ps.setInt(1, getUserIdByUsername(user_id)); // 사용자 ID
            ps.setInt(2, music.getMusic_id()); // 음악 ID

            // 쿼리 실행
            int result = ps.executeUpdate();
            if (result == 1) {
                return true; // 저장 성공
            }
        } catch (SQLException e) {
            System.out.println("[ 저장 시 예외 발생 ]: " + e.getMessage());
        }
        return false; // 저장 실패
    }
}
