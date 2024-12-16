package view;

import controller.HomeController;
import controller.PlaylistController;
import model.Dto.MusicDto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {
    static Scanner scan = new Scanner(System.in);

    // 싱글톤
    private static MainView mainView = new MainView();
    private MainView(){}
    public static MainView getInstance(){
        return mainView;
    }

    public void MainPrint() {
        System.out.println("[플레이리스트]"); // 사용자 환영 메시지
        while (true) {
            System.out.print("[ 1. 메인 2. 플레이리스트 3. 게시판 4. 마이페이지 5. 로그아웃 ] : ");
            int choose = scan.nextInt();
            if(choose == 1){
                HomeView.HomePrint();
            }else if (choose == 2){
               //PlaylistView.playlistPrint();
            }else if(choose == 3){
                // 게시판 로직
            } else if(choose == 4){

            }else if(choose == 5) {
                System.out.println("[ 로그아웃합니다. ]");
                System.exit(0);
                break;
            }else {
                System.out.println("[ 잘못된 입력입니다. ]");
            }
        }
    }
}
