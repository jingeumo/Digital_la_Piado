package controller;

import model.Dao.UserDao;
import model.Dto.UserDto;

import java.util.ArrayList;



public class UserController {
    // 싱글톤
    private static UserController userController = new UserController();

    private UserController() {
    }

    public static UserController getInstance() {
        return userController;
    }

    // 회원가입
    public boolean signUp(String username, String userid, String pwd, String email, String phoneNum) {
        UserDto userDto = new UserDto(username,userid,pwd,email,phoneNum);
        return UserDao.getInstance().signUp(userDto);
    }

//    public ArrayList<UserDto> login() {
//        ArrayList<UserDto> result = UserDao.getInstance().login();
//        return result;
//    }
}