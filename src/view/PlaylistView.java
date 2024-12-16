package view;

import controller.HomeController;
import controller.PlaylistController;
import model.Dto.MusicDto;
import model.Dto.PlaylistDto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaylistView {
    static Scanner scan = new Scanner(System.in);

    // 싱글톤
    private static PlaylistView playlistView = new PlaylistView();
    private PlaylistView(){}
    public static PlaylistView getInstance(){
        return playlistView;
    }

    public void playlistPrint(String userid, ArrayList<MusicDto> musicList) {
        while (true) {
            System.out.println("[플레이리스트]");
            System.out.print(" [1. 많이 들은 음악 순 2. 최근 들은 순 3. 구매한 노래 -1: 돌아가기] : ");
            int playlistchoose = getUserInput();
            if (playlistchoose == 1) {
                displayPopularPlaylist(musicList);
            } else if (playlistchoose == 2) {
                displayRecentPlaylist(musicList);
            } else if (playlistchoose == 3) {
                displayPurchasePlaylist(musicList);
            } else if (playlistchoose == -1) {
                break; // 돌아가기
            } else {
                System.out.println("[ 잘못된 입력입니다. 1번, 2번, 3번 또는 -1을 입력해주세요. ]");
            }
        }
    }

    void displayPurchasePlaylist(ArrayList<MusicDto> musicList) { // 구매한 음악 순
        ArrayList<PlaylistDto> purchasePlaylist = PlaylistController.getInstance().getPurchasePlaylist();
        displayPlaylist(purchasePlaylist, "[ 구매한 음악 ]", musicList);
    }

    void displayPopularPlaylist(ArrayList<MusicDto> musicList) { // 많이 들은 음악
        ArrayList<PlaylistDto> popularPlaylist = PlaylistController.getInstance().getPopularPlaylist();
        displayPlaylist(popularPlaylist, "[ 많이 들은 음악 ]", musicList);
    }

    void displayRecentPlaylist(ArrayList<MusicDto> musicList) { // 최근 들은 음악 순
        ArrayList<PlaylistDto> recentPlaylist = PlaylistController.getInstance().getRecentPlaylist();
        displayPlaylist(recentPlaylist, "[ 최근 들은 음악 ]", musicList);
    }


    void displayPlaylist(ArrayList<PlaylistDto> playlist, String title, String userid, ArrayList<MusicDto> musicList) {
        System.out.println("[" + title + "]");
        System.out.println("순위\t제목\t아티스트\t재생\t곡정보\t구매\t등록날짜");

        for (int index = 0; index < playlist.size(); index++) {
            PlaylistDto playlistItem = playlist.get(index);

            // 음악 ID로 MusicDto 객체 찾기
            MusicDto music = findMusicById(musicList, playlistItem.getMusicId());

            if (music != null) {
                System.out.printf("%d.\t%s\t%s\t%s\t%s\t%s\t%s\n",
                        index + 1,
                        music.getMusic_title(),
                        music.getMusic_artist_id(),  // 아티스트 ID
                        music.getMusic_play_status() == 0 ? "대기" : "재생",
                        music.getMusic_content(),
                        music.getMusic_purchase_status() == 0 ? music.getMusic_price() + "원" : "완료",
                        music.getMusic_release_date());
            }
        }

        handlePlaylistOption(playlist, musicList); // 옵션 처리
    }

    void handlePlaylistOption(ArrayList<PlaylistDto> playlist, ArrayList<MusicDto> musicList) {
        while (true) {
            System.out.print("[재생 및 구매할 곡 입력, -1 : 돌아가기] : ");
            int selection = getUserInput();

            if (selection == -1) {
                break; // 돌아가기
            } else if (selection > 0 && selection <= playlist.size()) { // 재생 및 구매할 곡 입력
                PlaylistDto selectedPlaylist = playlist.get(selection - 1);
                MusicDto selectedMusic = findMusicById(musicList, selectedPlaylist.getMusicId());

                if (selectedMusic != null) {
                    System.out.printf("[1 : %d번 노래를 재생, 2: %d번 노래를 구매 -1 : 돌아가기] : ", selection, selection);
                    int action = getUserInput();

                    if (action == 1) {
                        System.out.printf("[%d번 노래를 재생 중입니다.]\n", selection);
                        System.out.printf("[%d번 노래가 끝났습니다.]\n", selection);
                    } else if (action == 2) {
                        purchasePlaylistMusic(selectedPlaylist, musicList); // 선택한 플레이리스트와 음악 목록 전달
                    }
                }
            } else {
                System.out.println("[ 잘못된 입력입니다. 다시 입력해주세요. ]");
            }
        }
    }

    void purchasePlaylistMusic(PlaylistDto playlistItem, ArrayList<MusicDto> musicList) {
        // 음악 ID로 MusicDto 객체 찾기
        MusicDto music = findMusicById(musicList, playlistItem.getMusicId());

        // 이미 구매한 음악인지 확인
        if (music != null && music.getMusic_purchase_status() == 1) { // 1은 구매 완료 상태
            System.out.println("[ 이미 구매한 음악입니다. ]");
            return; // 메서드 종료
        }

        System.out.print("[ " + music.getMusic_title() + "번 노래를 구매하시겠습니까? 1: 구매, 2: 취소 ] : ");
        int purchaseChoice = getUserInput();

        if (purchaseChoice == 1) {
            // 구매 로직 (예: HomeController의 구매 메서드 호출)
            HomeController.getInstance().purchaseMusic(music);
            // 구매 상태 업데이트
            if (music != null) {
                music.setMusic_purchase_status(1); // 구매 완료 상태로 변경
            }
            System.out.println("[ " + music.getMusic_title() + "번 노래를 구매하셨습니다. ]");
        } else {
            System.out.println("[ 구매가 취소되었습니다. ]");
        }
    }

    MusicDto findMusicById(ArrayList<MusicDto> musicList, int musicId) {
        for (MusicDto music : musicList) {
            if (music.getMusic_id() == musicId) {
                return music; // 음악 ID가 일치하는 MusicDto 반환
            }
        }
        return null; // 일치하는 음악이 없으면 null 반환
    }

    static int getUserInput() {
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
