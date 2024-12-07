package view;

import controller.HomeController;
import model.Dto.MusicDto;

import java.util.ArrayList;
import java.util.Scanner;

public class HomeView {
    private Scanner scan = new Scanner(System.in);
    // 싱글톤
    private static HomeView homeView = new HomeView();
    private HomeView(){}
    public static HomeView getInstance(){
        return homeView;
    }

    public void HomePrint() {
        System.out.println("[홈 1. 현재 인기 음원차트 2. 최근 등록된 음악순] :");
        int homechoose = scan.nextInt();
        if(homechoose == 1) {
            displayPopularMusic();
        }
        else if(homechoose == 2){
            displayRecentMusic();
        } else {
            System.out.println("잘못된 입력입니다.");
        }
        // 홈 메뉴 돌아갈지 결정
        System.out.println("홈으로 돌아가려면 -1을 입력하세요, 계속하려면 다른 숫자를 입력하세요:");
        int backToHome == scan.nextInt();
        if(backToHome == 0){
            break;
        }
    }
}
