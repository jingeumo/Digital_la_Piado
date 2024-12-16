package model.Dto;

public class BoardDto {
    private int id;           // 게시글 ID
    private String title;     // 게시글 제목
    private String content;   // 게시글 내용
    private String author;    // 게시글 작성자

    // 기본 생성자
    public BoardDto(int boardId, String boardTitle, String boardContent, int userId) {}

    // 매개변수가 있는 생성자
    public BoardDto(int id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Getter 및 Setter 메서드
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getUserId() {
        return Integer.parseInt(null);
    }
}
