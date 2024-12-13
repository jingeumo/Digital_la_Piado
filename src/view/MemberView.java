package view;

import view.MainView;

public class MemberView {
    public static MemberView memberView = new MemberView();
    private MemberView() {}

    public static MemberView getInstance() {
        return memberView;
    }

    public void mainPage(int userNum) {
        MainView.getInstance().MainPrint(userNum); // 사용자 ID를 전달
    }
}
