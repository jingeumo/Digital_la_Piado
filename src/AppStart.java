import view.MemberView;

public class AppStart {
    public static void main(String[] args) {
        // 사용자 인증 로직을 통해 사용자 ID를 얻는 부분 (예시)
        int userNum = authenticateUser(); // 사용자 인증 메서드 호출
        MemberView.getInstance().mainPage(userNum); // 사용자 ID를 전달
    }

    private static int authenticateUser() {
        // 사용자 인증 로직 구현 (예: 로그인 후 사용자 ID 반환)
        // 임시로 1을 반환
        return 1; // 실제 사용자 ID를 반환하도록 수정 필요
    }
}
