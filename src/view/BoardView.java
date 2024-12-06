package view;

import controller.BoardController;
import model.Dto.BoardDto;

import java.util.List;
import java.util.Scanner;

public class BoardView {
    public static void main(String[] args) {
        BoardController controller = new BoardController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. 게시글 추가");
            System.out.println("2. 게시글 조회");
            System.out.println("3. 게시글 수정");
            System.out.println("4. 게시글 삭제");
            System.out.println("5. 공지사항 확인");
            System.out.println("6. 게시판으로 가기");
            System.out.println("7. 신고하기");
            System.out.println("0. 종료");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // 게시글 추가
                    System.out.print("제목: ");
                    String title = scanner.nextLine();
                    System.out.print("내용: ");
                    String content = scanner.nextLine();
                    System.out.print("작성자: ");
                    String author = scanner.nextLine();
                    controller.createBoard(title, content, author);
                    break;
                case 2:

                    List<BoardDto> boards = controller.getAllBoards();
                    for (BoardDto board : boards) {
                        System.out.println("ID: " + board.getId() + ", 제목: " + board.getTitle() + ", 작성자: " + board.getAuthor());
                    }
                    break;
                case 3:

                    System.out.print("수정할 게시글 ID: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine(); // 개행 문자 처리
                    System.out.print("새 제목: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("새 내용: ");
                    String newContent = scanner.nextLine();
                    System.out.print("새 작성자: ");
                    String newAuthor = scanner.nextLine();
                    controller.updateBoard(new BoardDto(idToUpdate, newTitle, newContent, newAuthor));
                    break;
                case 4:
                    // 게시글 삭제
                    System.out.print("삭제할 게시글 ID: ");
                    int idToDelete = scanner.nextInt();
                    controller.deleteBoard(idToDelete);
                    break;

                case 5:
                    //공지사항 확인
                    System.out.println("공지사항 확인하기");
                    int gongjisanhang = scanner.nextInt();
                    controller.gongjisanhang(gongjisanhang);
                    break;

                case 6:
                    // 게시판으로 가기
                    controller.goToBoard();
                    break;
                case 7:
                    // 신고하기
                    System.out.print("신고할 게시글 ID: ");
                    int boardIdToReport = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("신고 사유: ");
                    String reason = scanner.nextLine();
                    controller.reportBoard(boardIdToReport, reason);
                    System.out.println("신고완료!");
                    break;
                case 0:
                    System.out.println("프로그램 종료");
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }
}