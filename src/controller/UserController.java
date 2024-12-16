package controller;

import model.Dao.UserDao;
import model.Dto.UserDto;

public class UserController {
    // 싱글톤
    private static UserController userController = new UserController();
    private UserController() {}
    public static UserController getInstance() {return userController;}

    // 회원가입
    public boolean signUp(String username, String userid, String pwd, String email, String phoneNum) {
        UserDto userDto = new UserDto(username,userid,pwd,email,phoneNum);
        return UserDao.getInstance().signUp(userDto);
    }
    // 로그인
    public UserDto login(String userid, String password) {
        UserDto userDto = new UserDto(userid, password);
        return UserDao.getInstance().login(userDto);
    }
}