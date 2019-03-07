package com.titanium.moodmusic.ui.fragments.favoriteAlbums;

import android.util.Log;

import com.titanium.moodmusic.data.db.dao.MusicDao;
import com.titanium.moodmusic.data.db.entity.FavoriteAlbumTable;
import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/** Класс отвечает за взаимодействие с данными, их получение из API, DB.. **/

public class FavoriteAlbumsInteractor implements IFavoriteAlbumsInteractor {

    private MusicDao musicDao;

    public FavoriteAlbumsInteractor(MusicDao musicDao) {
        this.musicDao = musicDao;
    }

    @Override
    public Single<List<FavoriteAlbumTable>> getFavoriteAlbums() {
        return musicDao.getAllAlbums();
    }

    @Override
    public void saveAlbumsToDatabase(final List<FavoriteAlbum> favoriteAlbums) {
        musicDao.deleteAllAlbums();

        for (FavoriteAlbum item : favoriteAlbums) {
            FavoriteAlbumTable favoriteAlbumTable = new FavoriteAlbumTable();
            favoriteAlbumTable.setIdAlbum(item.getAlbumId());
            favoriteAlbumTable.setNameAlbum(item.getNameAlbum());
            favoriteAlbumTable.setCountSongsInAlbum(item.getCountSongsInAlbum());
            favoriteAlbumTable.setTrackList(item.getTrackList());

            musicDao.insertAlbum(favoriteAlbumTable);
        }
    }

    @Override
    public List<FavoriteAlbum> searchAlbum(String nameAlbum) {
        return null;
    }
}
