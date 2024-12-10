package view;

import controller.UserController;

import java.util.Scanner;

public class MemberView {
    static Scanner scan = new Scanner(System.in);
    public static void mainPage(){
        while (true){
            System.out.print("1. 회원가입 2. 로그인 3. 종료 : ");
            int choose = scan.nextInt();
            if ( choose == 1 ){
                signUp( scan );
            } else if ( choose == 2 ) {
                //UserController.getInstance().login();
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
} // mainPage

//private static void login(Scanner scan) {
//    System.out.print("아이디: "); String userid = scan.nextLine();
//    System.out.print("비밀번호: "); String pwd = scan.nextLine();
//
//    for (User user : userList) {
//        if (user.userid.equals(userid) && user.pwd.equals(pwd)) {
//            loggedInUser = user;
//            System.out.println("로그인 성공: " + username);
//            return;
//        }
//    }
//    System.out.println("로그인 실패: 사용자 이름 또는 비밀번호가 잘못되었습니다.");
//} // m end
//
//private static void logout() {
//    if (loggedInUser != null) {
//        System.out.println(loggedInUser.userid + "님이 로그아웃되었습니다.");
//        loggedInUser = null;
//    } else {
//        System.out.println("로그인 상태가 아닙니다.");
//    }
//}


/*

 */