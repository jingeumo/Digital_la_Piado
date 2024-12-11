package view;

import controller.HomeController;
import controller.PlaylistController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {
    Scanner scan = new Scanner(System.in);

    // 싱글톤
    private static MainView mainView = new MainView();
    private MainView(){}
    public static MainView getInstance(){
        return mainView;
    }

    public void MainPrint(){
        while(true){
            System.out.print("[ 1. 메인 2. 플레이리스트 3. 게시판 4. 마이페이지 5. 로그아웃 ] : ");
            int choose = getUserInput();
            MenuSelection(choose);
        }
    }

    public void MenuSelection(int choose) {
        switch (choose){
            case 1:
                HomeView.getInstance().HomePrint();
                break;
            case 2:
                //PlayhistoryController.PrintPlaylistView();
                break;
            case 3:
                // 게시판 로직
                break;
            case 4:
                break;
            case 5:
                System.out.println("[ 로그아웃합니다. ]");
                System.exit(0);
                break;
            default:
                System.out.println("[ 잘못된 입력입니다. ]");
        }
    }
    private int getUserInput() {
        while (true) {
            try {
                return scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("[ 잘못된 입력입니다. 숫자를 입력해주세요. ]");
                scan.next(); // 잘못된 입력 제거
            }
        }
    }
}
