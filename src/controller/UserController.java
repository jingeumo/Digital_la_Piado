package controller;

import model.Dao.UserDao;
import model.Dto.UserDto;

import java.util.ArrayList;



public class UserController {
    // 싱글톤
    private static UserController userController = new UserController();
    private UserController() {}
    public static UserController getInstance() {return userController;}

    private UserController() {
    }

    public static UserController getInstance() {
        return userController;
    }

    // 로그인 성공한 회원아이디 저장
    public String loginId = null; // null이면 비어있는, null이 아니면 로그인 성공한 아이디 넣을 예정

    // 회원가입
    public boolean signUp(String username, String userid, String pwd, String email, String phoneNum) {
        UserDto userDto = new UserDto(username,userid,pwd,email,phoneNum);
        return UserDao.getInstance().signUp(userDto);
    }

    public boolean login(String userid, String pwd) {
        UserDto userDto = new UserDto( userid, pwd);
        boolean result = UserDao.getInstance().login( userDto );
        if ( result ){
            loginId = userid;
        }
        return result;
    }

    public void logout(){
        loginId = null;
    }
}