package view;

import controller.BoardController;
import model.Dto.BoardDto;

import java.util.List;
import java.util.Scanner;

public class BoardView {
    public static void main(String[] args) {
        BoardController controller = new BoardController();
        Scanner scanner = new Scanner(System.in);

        // 사용자 정보 예시
        String userId = "admin123"; // 예시 사용자 ID
        String username = "john_doe"; // 예시 사용자 이름
        int age = 20; // 예시 나이
        String phone = "01012341234"; // 예시 전화번호
        String email = "admin123@naver.com"; // 예시 이메일
        String address = "인천광역시 부평구"; // 예시 주소
        int userGrade = 1; // 예시 사용자 등급 (1: 개인)

        while (true) {
            System.out.println("-------------------------[ 게시판 등록 ]----------------------------");
            System.out.println("1. 홈 2. 플레이리스트 3. 게시판 4. 마이페이지 5. 로그아웃 : 3");
            System.out.print("[1. 게시글 작성 2. 게시글 출력 -1 : 돌아가기] : ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            switch (choice) {
                case 1:
                    // 게시글 작성
                    System.out.print("게시판 제목 : ");
                    String title = scanner.nextLine();
                    System.out.print("게시판 내용 : ");
                    String content = scanner.nextLine();
                    controller.createBoard(title, content, username);
                    System.out.println("[게시판 작성이 완료되었습니다.]");
                    break;

                case 2:
                    // 게시글 출력
                    System.out.println("--------------------------[ 게시글 출력 ]---------------------------");
                    List<BoardDto> boards = controller.getAllBoards();
                    if (boards.isEmpty()) {
                        System.out.println("게시글이 없습니다.");
                        System.out.print("[1. 게시글 작성, 2. 게시글 출력 -1 : 돌아가기] : -1");
                    } else {
                        for (BoardDto board : boards) {
                            System.out.println("게시글 번호 : " + board.getId() + ", 작성자 : " + board.getAuthor() + ", 게시글 제목 : " + board.getTitle());
                        }
                        System.out.print("[1. 게시글 작성, 2. 게시글 출력, 3. 게시글 선택 4. 게시글 수정 5. 게시글 삭제 -1 : 돌아가기] : ");
                        int subChoice = scanner.nextInt();
                        scanner.nextLine(); // 개행 문자 처리

                        if (subChoice == 3) {
                            // 게시글 선택
                            System.out.print("어떤 게시글 번호를 선택하시겠습니까 ? : ");
                            int boardId = scanner.nextInt();
                            scanner.nextLine(); // 개행 문자 처리
                            BoardDto selectedBoard = controller.getBoardById(boardId);
                            if (selectedBoard != null) {
                                System.out.println("--------------------------[ 게시글 선택 ]--------------------------");
                                System.out.println("게시글 번호 : " + selectedBoard.getId());
                                System.out.println("작성자 : " + selectedBoard.getAuthor());
                                System.out.println("게시글 제목 : " + selectedBoard.getTitle());
                                System.out.println("게시글 내용 : " + selectedBoard.getContent());
                            } else {
                                System.out.println("해당 게시글이 존재하지 않습니다.");
                            }
                        } else if (subChoice == 4) {
                            // 게시글 수정
                            System.out.print("어떤 게시글을 수정하시겠습니까? 해당 게시글 번호를 입력하십시오. ? : ");
                            int idToUpdate = scanner.nextInt();
                            scanner.nextLine(); // 개행 문자 처리
                            System.out.print("새 제목: ");
                            String newTitle = scanner.nextLine();
                            System.out.print("새 내용: ");
                            String newContent = scanner.nextLine();
                            controller.updateBoard(new BoardDto(idToUpdate, newTitle, newContent, username));
                        }
                    }
                    break;

                case -1:
                    System.out.println("돌아갑니다.");
                    break;

                case 4:
                    // 마이페이지
                    System.out.println("--------------------------[ 마이페이지 ] --------------------------");
                    System.out.println("아이디 : " + userId);
                    System.out.println("사용자 이름 : " + username);
                    System.out.println("성별 : " + (0 == 0 ? "남성" : "여성")); // 예시로 남성
                    System.out.println("나이 : " + age);
                    System.out.println("전화번호 : " + phone);
                    System.out.println("이메일 : " + email);
                    System.out.println("주소 : " + address);
                    System.out.println("등급 : " + (userGrade == 1 ? "개인" : "아티스트"));
                    System.out.println("-1 를 입력하면 되돌아갑니다 : -1");
                    int backChoice = scanner.nextInt();
                    if (backChoice == -1) {
                        System.out.println("돌아갑니다.");
                    }
                    break;

                case 5:
                    System.out.println("로그아웃");
                    return;

                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }
}
