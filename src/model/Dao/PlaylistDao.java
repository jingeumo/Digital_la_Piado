package model.Dao;
import model.Dto.MusicDto;
import model.Dto.PlaylistDto;

import java.sql.*;
import java.util.ArrayList;

public class PlaylistDao {
    private Connection conn;
    private static PlaylistDao playlistDao = new PlaylistDao();
    private PlaylistDao(){
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
    public static PlaylistDao getInstance(){return playlistDao;}

    public ArrayList<PlaylistDto> getPopularPlaylist() {
        ArrayList<PlaylistDto> popularPlayList = new ArrayList<>();
        try{
            String sql =  "SELECT p.play_id, p.played_at, u.user_num, m.* " +
                    "FROM Playlist p " +
                    "JOIN Users u ON p.user_num = u.user_num " +
                    "JOIN Music m ON p.music_id = m.music_id " +
                    "ORDER BY m.music_play_status DESC LIMIT 10";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                MusicDto musicDto = new MusicDto(
                        rs.getInt("music_id"),
                        rs.getString("music_title"),
                        rs.getInt("music_artist_id"),
                        rs.getInt("music_play_status"),
                        rs.getString("music_content"),
                        rs.getInt("music_save_status"),
                        rs.getInt("music_price"),
                        rs.getInt("music_purchase_status"),
                        rs.getDate("music_release_date")
                );
                PlaylistDto playlistDto = new PlaylistDto(
                        rs.getInt("play_id"),
                        rs.getTimestamp("played_at"),
                        rs.getInt("user_num"),
                        musicDto
                );
                popularPlayList.add(playlistDto);
            }
        }catch (SQLException e) {
            e.getMessage();
            System.out.println("[ 인기 많은 순으로 출력 시 예외 발생 ]");
        }
        return popularPlayList;
    }


    public ArrayList<PlaylistDto> getRecentPlaylist() {
        ArrayList<PlaylistDto> recentPlayList = new ArrayList<>();
        try{
            String sql =  "SELECT p.play_id, p.played_at, u.user_num, m.* " +
                    "FROM Playlist p " +
                    "JOIN Users u ON p.user_num = u.user_num " +
                    "JOIN Music m ON p.music_id = m.music_id " +
                    "ORDER BY p.played_at DESC LIMIT 10";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // MusicDto 생성
                MusicDto musicDto = new MusicDto(
                        rs.getInt("music_id"),
                        rs.getString("music_title"),
                        rs.getInt("music_artist_id"),
                        rs.getInt("music_play_status"),
                        rs.getString("music_content"),
                        rs.getInt("music_save_status"),
                        rs.getInt("music_price"),
                        rs.getInt("music_purchase_status"),
                        rs.getDate("music_release_date")
                );

                // PlaylistDto 생성 및 추가
                PlaylistDto playlistDto = new PlaylistDto(
                        rs.getInt("play_id"),
                        rs.getTimestamp("played_at"),
                        rs.getInt("user_num"),
                        musicDto
                );
                recentPlayList.add(playlistDto);
            }
        }catch (SQLException e) {
            e.getMessage();
            System.out.println("[ 인기 많은 순으로 출력 시 예외 발생 ]");
        }
        return recentPlayList;
    }

    public ArrayList<PlaylistDto> getPurchasePlaylist() {
        ArrayList<PlaylistDto> purchasePlayList = new ArrayList<>();
        try{
            String sql = "SELECT p.purchase_id, p.purchase_date, p.user_num, m.* " +
                    "FROM Purchase p " +
                    "JOIN Users u ON p.user_num = u.user_num " +
                    "JOIN Music m ON p.music_id = m.music_id " +
                    "ORDER BY p.purchase_date DESC LIMIT 10";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // MusicDto 생성
                MusicDto musicDto = new MusicDto(
                        rs.getInt("music_id"),
                        rs.getString("music_title"),
                        rs.getInt("music_artist_id"),
                        rs.getInt("music_play_status"),
                        rs.getString("music_content"),
                        rs.getInt("music_save_status"),
                        rs.getInt("music_price"),
                        rs.getInt("music_purchase_status"),
                        rs.getDate("music_release_date")
                );

                // PlaylistDto 생성 및 추가
                PlaylistDto playlistDto = new PlaylistDto(
                        rs.getInt("purchase_id"),
                        rs.getTimestamp("purchase_date"),
                        rs.getInt("user_num"),
                        musicDto
                );
                purchasePlayList.add(playlistDto);
            }
        }catch (SQLException e) {
            e.getMessage();
            System.out.println("[ 인기 많은 순으로 출력 시 예외 발생 ]");
        }
        return purchasePlayList;
    }
}
