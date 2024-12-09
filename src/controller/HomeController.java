package controller;

import model.Dao.HomeDao;
import model.Dto.MusicDto;
import view.HomeView;

import java.util.ArrayList;
import java.util.List;

public class HomeController {
    private static HomeController homeController = new HomeController();
    private HomeController(){}
    public static HomeController getInstance() {return homeController;}

    /**
     * [홈]
     * [1. 현재 인기 음원차트 2. 최근 등록된 음악순] : 1
     * [현재 인기 음원차트] = view 클릭 수에 따라 변경됨, music 정렬 사용할 것
     * 순위     제목    아티스트    재생  	곡정보(...) 저장   구매
     * 1. 	 음악제목1    이이름1	   대기       곡설명     대기    1350원
     * 2.   음악제목2    이이름2	   대기       곡설명     대기    1350원
     */
    // 홈 출력
    public ArrayList<MusicDto> HomePrint() {
        ArrayList<MusicDto> result = HomeDao.getInstance().HomePrint();
        return result;
    }
}
