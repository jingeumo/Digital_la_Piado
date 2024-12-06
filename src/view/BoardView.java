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
            System.out.println("1. 게시글 추가 2. 게시글 조회 3. 게시글 수정 4. 게시글 삭제 5. 공지사항 확인 6. MY Page 7. 신고하기 0. 종료 ");
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
                    // 공지사항 확인
                    System.out.print("공지사항을 확인하시겠습니까? (Yes or No): ");
                    String response = scanner.nextLine().trim().toLowerCase(); // 입력받은 문자열을 소문자로 변환
                    if (response.equals("yes")) {
                        // 공지사항을 가져와서 출력
                        List<String> announcements = controller.getAnnouncements(); // 공지사항을 가져오는 메서드 호출
                        if (announcements.isEmpty()) {
                            System.out.println("현재 공지사항이 없습니다.");
                        } else {
                            System.out.println("공지사항:");
                            for (String announcement : announcements) {
                                System.out.println("- " + announcement);
                            }
                        }
                    } else if (response.equals("no")) {
                        System.out.println("공지사항 확인을 취소했습니다.");
                    } else {
                        System.out.println("잘못된 입력입니다. 'Yes' 또는 'No'를 입력하세요.");
                    }
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