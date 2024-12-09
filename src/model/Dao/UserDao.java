package model.Dao;

import java.io.File;
import java.io.IOException;

public class UserDao {
    // 싱글톤
    private static UserDao userDao = new UserDao();
    private UserDao(){

        File file = new File("jdbc: ");
        // [2] 객체화 한 파일이 존재 하는지 확인
        if( file.exists() ){ // - 지정한 경로의 파일이 있다.
            // - 싱글톤(static) 이 생성될때( 프로그램이 실행될때 )
            fileLoad(); // 파일 로드
        }else{ // - 지정한 경로의 파일이 없다.
            try {
                file.createNewFile(); // .createNewFile() : 파일 생성 함수
            }catch ( IOException e ){
                e.printStackTrace();
            }
        } // else end
    } // 생성자 end
}
