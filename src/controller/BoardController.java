package controller;

import model.Dto.BoardDto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardController {
    private Connection connection;

    // 데이터베이스 연결 설정
    public BoardController() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 데이터베이스 연결 (URL, 사용자명, 비밀번호는 실제 DB에 맞게 수정)
            String url = "jdbc:mysql://localhost:3306/project01"; // 데이터베이스 URL
            String user = "root"; // DB 사용자명
            String password = "tlarjsqh123"; // DB 비밀번호

            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.getMessage();
            System.out.println("JDBC 드라이버 찾을 수 없음");
        }
        catch (SQLException e) {
            e.getMessage();
            System.out.println("[ sql 연결 문제발생 ]");
        }
    }

    // 게시글 생성
    public void createBoard(String title, String content, String author) {
        String sql = "INSERT INTO board (board_title, board_content, user_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setInt(3, getUserIdByUsername(author)); // 작성자 ID 가져오기
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
                        getUsernameById(rs.getInt("user_id")) // 작성자 이름 가져오기
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
                            getUsernameById(rs.getInt("user_id"))
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

    // 사용자 이름으로 ID 가져오기
    private int getUserIdByUsername(String username) {
        String sql = "SELECT user_id FROM users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 사용자 ID가 없을 경우
    }

    // 사용자 ID로 이름 가져오기
    private String getUsernameById(int userId) {
        String sql = "SELECT username FROM users WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Unknown"; // 사용자 이름이 없을 경우
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
