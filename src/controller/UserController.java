package controller;

import model.Dao.UserDao;
import model.Dto.UserDto;

import java.util.ArrayList;



public class UserController {
    // 싱글톤
    private static UserController userController = new UserController();
    private UserController(){}
    public static UserController getInstance(){
        return userController;
    }

    // 회원가입
    public boolean SignUp( String username, String userid, int pwd, String email, int phoneNum ){
        UserDto userDto = new UserDto( username, userid, pwd, email, phoneNum );
        return UserDao.getInstance().SignUp( UserDto );
    }

}
