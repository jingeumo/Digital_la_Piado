package model.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Dto.BoardDto;

public class BoardDao {
    private static final String URL = "jdbc:mysql://localhost:3306/boards"; // 데이터베이스 URL
    private static final String USER = "root"; // 사용자명
    private static final String PASSWORD = "tlarjsqh123"; // 비밀번호

    // 게시글 추가
    public void createBoard(BoardDto board) {
        String sql = "INSERT INTO boards (title, content, author) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pwd1 = conn.prepareStatement(sql)) {
            pwd1.setString(1, board.getTitle());
            pwd1.setString(2, board.getContent());
            pwd1.setString(3, board.getAuthor());
            pwd1.executeUpdate();
            System.out.println("게시글이 추가되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 게시글 조회
    public List<BoardDto> getAllBoards() {
        List<BoardDto> boards = new ArrayList<>();
        String sql = "SELECT * FROM boards";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String author = rs.getString("author");
                boards.add(new BoardDto(id, title, content, author));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boards;
    }

    // 게시글 수정
    public void updateBoard(BoardDto board) {
        String sql = "UPDATE boards SET title = ?, content = ?, author = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pwd2 = conn.prepareStatement(sql)) {
            pwd2.setString(1, board.getTitle());
            pwd2.setString(2, board.getContent());
            pwd2.setString(3, board.getAuthor());
            pwd2.setInt(4, board.getId());
            pwd2.executeUpdate();
            System.out.println("게시글이 수정되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 게시글 삭제
    public void deleteBoard(int id) {
        String sql = "DELETE FROM boards WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("게시글이 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}



