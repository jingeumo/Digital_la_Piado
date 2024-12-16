package view;

import controller.HomeController;
import model.Dto.MusicDto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HomeView {
    static Scanner scan = new Scanner(System.in);
    // 싱글톤
    private static HomeView homeView = new HomeView();
    private HomeView() {}
    public static HomeView getInstance() {
        return homeView;
    }

    static void HomePrint() {
        while (true) {
            System.out.print("[ 홈 1. 현재 인기 음원차트 2. 최근 등록된 음악순 ] : ");
            int homechoose = scan.nextInt();
            if (homechoose == 1) {
                displayPopularMusic(); // 사용자 ID 전달
            } else if (homechoose == 2) {
                displayRecentMusic(); // 사용자 ID 전달
            } else {
                System.out.println("[ 잘못된 입력입니다. 1번 아니면 2번을 입력해주세요. ]");
                continue;
            }
        }
    }

    static void displayPopularMusic() {
        ArrayList<MusicDto> popularMusicList = HomeController.getInstance().getPopularMusic();
        displayMusicList(popularMusicList, "[ 인기 음원 차트 ]");
    }

    static void displayRecentMusic() {
        ArrayList<MusicDto> recentMusicList = HomeController.getInstance().getRecentMusic();
        displayMusicList(recentMusicList, "[ 최근 등록된 음악 ]");
    }

    static void displayMusicList(ArrayList<MusicDto> musicList, String title) {
        System.out.println("[" + title + "]");
        System.out.println("출력테스트 -----------------------------------------------------");
        System.out.println("순위\t제목\t아티스트\t재생\t곡정보\t저장\t가격\t구매\t등록날짜");


    }

    void handleMusicOptions(ArrayList<MusicDto> musicList) {
        while (true) {
            System.out.print("[ 재생 및 구매할 곡 입력, -1 : 돌아가기, -2: 플레이리스트 저장 ] : ");
            int choice = scan.nextInt();

            if (choice == -1) {
                return; // 이전 메뉴로 돌아가기
            } else if (choice == -2) {
                System.out.print("[ 저장할 곡 입력 ]: ");
                int musicChoice = scan.nextInt(); // 저장할 곡 선택

                if (musicChoice > 0 && musicChoice <= musicList.size()) {
                    boolean result = HomeController.getInstance().saveToPlaylist(musicList.get(musicChoice));
                    if(result) {

                    }
//                  MusicDto selectedMusic = musicList.get(musicChoice - 1);
//                  saveToPlaylist(selectedMusic);; // 선택한 음악을 저장
                } else {
                    System.out.println("[ 잘못된 입력입니다. 다시 입력해주세요. ]");
                }
                continue; // 다시 입력 받기
            } else if (choice > 0 && choice <= musicList.size()) {
                MusicDto selectedMusic = musicList.get(choice - 1);
                handleMusicAction(selectedMusic);
            } else {
                System.out.println("[ 잘못된 입력입니다. 다시 입력해주세요. ]");
            }
        }
    }

    void handleMusicAction(MusicDto music) {
        System.out.print("[ 1 : " + music.getMusic_title() + "를 재생, 2: " + music.getMusic_title() + "를 구매 -1 : 돌아가기 ] : ");
        int action = scan.nextInt();

        if (action == -1) {
            return; // 이전 메뉴로 돌아가기
        } else if (action == 1) {
            System.out.println("[ " + music.getMusic_title() + " 노래를 재생 중입니다. ]");
            System.out.println("[ " + music.getMusic_title() + " 노래가 끝났습니다. ]");
        } else if (action == 2) {
            purchaseMusic(music);
        } else {
            System.out.println("[ 잘못된 입력입니다. ]");
        }
    }

    void purchaseMusic(MusicDto music) {
        // 이미 구매한 음악인지 확인
        if (music.getMusic_purchase_status() == 1) { // 1은 구매 완료 상태
            System.out.println("[ 이미 구매한 음악입니다. ]");
            return; // 메서드 종료
        }
        System.out.print("[ " + music.getMusic_title() + "번 노래를 구매하시겠습니까? 1: 구매, 2: 취소 ] : ");
        int purchaseChoice = scan.nextInt();
        if (purchaseChoice == 1) {
            HomeController.getInstance().purchaseMusic(music);
            System.out.println("[ " + music.getMusic_title() + "번 노래를 구매하셨습니다. ]");
        } else {
            System.out.println("[ 구매가 취소되었습니다. ]");
        }
    }

    void saveToPlaylist(MusicDto music) {// 저장 유효성검사
        // 이미 저장된 음악인지 확인
        if (music.getMusic_save_status() == 1) { // 1은 저장 완료 상태
            System.out.println("[ 해당 곡이 이미 플레이리스트에 저장되었습니다. ]");
            return; // 메서드 종료
        } else if (music.getMusic_save_status() == 0){
            System.out.println("[ 해당 곡을 내 플레이리스트에 저장합니다. ]");
            boolean result = HomeController.getInstance().saveToPlaylist(music);
            System.out.println("[ 저장 완료! ]");
        }
    }
}
