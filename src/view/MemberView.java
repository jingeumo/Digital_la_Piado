package view;

import controller.UserController;
import model.Dto.MusicDto;
import model.Dto.UserDto;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberView {
    Scanner scan = new Scanner(System.in);
    // 싱글톤
    private static MemberView memberView = new MemberView();
    private MemberView(){}
    public static MemberView getInstance(){
        return memberView;
    }
    private UserDto loggedInUser = null;// 현재 로그인 사용자

    public void mainPage(){
        while (true){
            System.out.print("1. 회원가입 2. 로그인 3. 종료 : ");
            int choose = scan.nextInt();
            if ( choose == 1 ){
                signUp( scan );
            } else if ( choose == 2 ) {
                login( scan );
            } else if ( choose == 3 ) {
                System.out.println("프로그램을 종료합니다.");
                scan.close();
                return;
            } else
                System.out.println("잘못된 선택입니다.");
        }
        // if
    } // while

    private static void signUp(Scanner scan) {
        System.out.print("사용자 이름: ");   String username = scan.next();
        System.out.print("아이디: ");   String userid = scan.next();
        System.out.print("비밀번호: ");   String pwd = scan.next();
        System.out.print("이메일: ");   String email = scan.next();
        System.out.print("전화번호: ");   String phoneNum = scan.next();

        boolean result = UserController.getInstance().signUp(username, userid, pwd, email, phoneNum);
        if( result ){
            System.out.println("회원가입 성공");

        }else{
            System.out.println("회원가입 실패");
        }
    } // m end

    private void login(Scanner scan) { // 로그인
        System.out.print("아이디: ");
        String userid = scan.nextLine();
        System.out.print("비밀번호: ");
        String pwd = scan.nextLine();

        loggedInUser = UserController.getInstance().login(userid, pwd);
        if (loggedInUser != null) {
            System.out.println("로그인 성공: " + loggedInUser.getUsername());
            // 사용자 고유 번호 전달, 음악 리스트 초기화
            int userNum = Integer.parseInt(loggedInUser.getUserId());
            ArrayList<MusicDto> musicList = new ArrayList<>();
            MainView.getInstance().MainPrint(userNum, musicList);
        } else {
            System.out.println("로그인 실패: 사용자 이름 또는 비밀번호가 잘못되었습니다.");
        }
    } // login // m end
} // mainPage



