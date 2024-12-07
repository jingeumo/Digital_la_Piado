package view;

import java.util.Scanner;

public class MainView {
    // 싱글톤
    private MainView(){}
    private static MainView mainView = new MainView();
    public static MainView getInstance(){
        return mainView;
    }
    Scanner scan = new Scanner(System.in);

    public void display(){ // 1. 홈 2. 플레이리스트 3. 게시판 4. 마이페이지 5. 로그아웃
        while(true){
            System.out.print(" 1. 홈 2. 플레이리스트 3. 게시판 4. 마이페이지 5. 로그아웃 ");
            int mainchoose = scan.nextInt();
            if(mainchoose == 1) {
                HomeView.getInstance().HomePrint();
            }
            else if(mainchoose == 2){
                //PlaylistView.PlaylistPrint();
            }
            else if(mainchoose == 3){
                // BoardView.boardPrint();
            }
            else if(mainchoose == 4){
                // MyPageView.mypagePrint();
            }
            else if (mainchoose == 5){
                System.out.println("로그아웃을 진행합니다.");
                break;
            } else{
                System.out.println("숫자를 다시 입력하세요.");
                continue;
            }
        }
    }
}
