package model.Dao;

import model.Dto.BoardDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    private Connection connection;

    // 데이터베이스 연결 설정
    public BoardDAO() {
        try {
            String url = "jdbc:mysql://localhost:3306/boards"; // 데이터베이스 URL
            String user = "root"; // DB 사용자명
            String password = "tlarjsqh123"; // DB 비밀번호

            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 게시글 생성
    public void createBoard(BoardDto board) {
        String sql = "INSERT INTO board (board_title, board_content, user_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setInt(3, board.getUserId()); // 게시글 작성자 ID
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 모든 게시글 조회
    public List<BoardDto> getAllBoards() {
        List<BoardDto> boards = new ArrayList<>();
        String sql = "SELECT board_id, board_title, board_content, user_id FROM board";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                boards.add(new BoardDto(
                        rs.getInt("board_id"),
                        rs.getString("board_title"),
                        rs.getString("board_content"),
                        rs.getInt("user_id") // 작성자 ID
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boards;

    }

    // 게시글 ID로 게시글 조회
    public BoardDto getBoardById(int boardId) {
        String sql = "SELECT board_id, board_title, board_content, user_id FROM board WHERE board_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, boardId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new BoardDto(
                            rs.getInt("board_id"),
                            rs.getString("board_title"),
                            rs.getString("board_content"),
                            rs.getInt("user_id") // 작성자 ID
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 게시글 수정
    public void updateBoard(BoardDto board) {
        String sql = "UPDATE board SET board_title = ?, board_content = ? WHERE board_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setInt(3, board.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 게시글 삭제
    public void deleteBoard(int boardId) {
        String sql = "DELETE FROM board WHERE board_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, boardId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 데이터베이스 연결 종료
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
