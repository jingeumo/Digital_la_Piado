package view;

import controller.UserController;
import model.Dto.MusicDto;
import model.Dto.UserDto;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberView {
    static Scanner scan = new Scanner(System.in);

    public static void mainPage() {
        while (true) {
            String loginIn = UserController.getInstance().loginId;
            System.out.println(  loginIn == null ? "로그인전" : loginIn+"님 로그인" );// 로그인 상태 확인용

            System.out.print("1. 회원가입 2. 로그인 3. 종료 : 4. 로그아웃");
            int choose = scan.nextInt();
            if (choose == 1) {
                signUp(scan);
            } else if (choose == 2) {
                login(scan);
            } else if (choose == 3) {
                System.out.println("프로그램을 종료합니다.");
                scan.close();
                return;
            } else if (choose == 4) {
                logout();
            } else {
                System.out.println("잘못된 선택입니다.");
                return;
            }
            // if
        } // while
    } // m end


    private static void signUp(Scanner scan) {
        scan.nextLine();
        System.out.print("사용자 이름: ");
        String username = scan.nextLine();
        System.out.print("아이디: ");
        String userid = scan.nextLine();
        System.out.print("비밀번호: ");
        String pwd = scan.next();
        System.out.print("이메일: ");
        String email = scan.nextLine();
        scan.nextLine();
        System.out.print("전화번호: ");
        String phoneNum = scan.next();

        boolean result = UserController.getInstance().signUp(username, userid, pwd, email, phoneNum);
        if (result ){
            System.out.println("회원가입 성공.");
        } else {
            System.out.println("회원가입 실패: 관리자에게 문의하세요.");
        }

    } // m end

    private static void login(Scanner scan) {
        scan.nextLine();
        System.out.print("아이디: ");
        String userid = scan.nextLine();
        System.out.print("비밀번호: ");
        String pwd = scan.nextLine();

        boolean result = UserController.getInstance().login( userid, pwd );
        if (result ){
            System.out.println("로그인 성공.");
            BoardView.mainPage();
        } else {
            System.out.println("로그인 실패: 사용자 이름 또는 비밀번호가 잘못되었습니다.");
        }

    } // m end

    private static void logout() {
        UserController.getInstance().logout();
        System.out.println("로그아웃 했습니다");
    }

} // c end
/*
.
 */