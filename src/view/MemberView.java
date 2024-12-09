package view;

import controller.MainController;

public class MemberView {
    public static MemberView memberView = new MemberView();
    private MemberView(){}
    public static MemberView getInstance() {
        return memberView;
    }
    public void mainPage(){
        MainController.getInstance().MainPrint();
    }
}
