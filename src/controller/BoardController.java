package controller;

import model.Dao.BoardDao;
import model.Dto.BoardDto;

import java.util.List;

public class BoardController {
    private BoardDao boardDao;

    public BoardController() {
        this.boardDao = new BoardDao();
    }

    public void createBoard(String title, String content, String author) {
        BoardDto board = new BoardDto(0, title, content, author);
        boardDao.createBoard(board);
    }

    public List<BoardDto> getAllBoards() {
        return boardDao.getAllBoards();
    }

    public void updateBoard(BoardDto board) {
        boardDao.updateBoard(board);
    }

    public void deleteBoard(int id) {
        boardDao.deleteBoard(id);
    }

    public void goToBoard() {
    }

    public void reportBoard(int boardIdToReport, String reason) {

    }

    public void gongjisanhang(int gongji, String gongji1) {

    }


    public void gongjisanhang(int gongjisanhang) {
    }
}
