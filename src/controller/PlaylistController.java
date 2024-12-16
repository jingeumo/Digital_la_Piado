package controller;

import model.Dao.HomeDao;
import model.Dao.PlaylistDao;
import model.Dto.MusicDto;
import model.Dto.PlaylistDto;

import java.util.ArrayList;

public class PlaylistController {
    private static PlaylistController playlistController = new PlaylistController();
    private PlaylistController(){}
    public static PlaylistController getInstance() {return playlistController;}


    public ArrayList<PlaylistDto> getPopularPlaylist() {
        ArrayList<PlaylistDto> result = PlaylistDao.getInstance().getPopularPlaylist();
        return result;
    }

    public ArrayList<PlaylistDto> getRecentPlaylist() {
        ArrayList<PlaylistDto> result = PlaylistDao.getInstance().getRecentPlaylist();
        return result;
    }

    public ArrayList<PlaylistDto> getPurchasePlaylist() {
        ArrayList<PlaylistDto> result = PlaylistDao.getInstance().getPurchasePlaylist();
        return result;
    }
}
