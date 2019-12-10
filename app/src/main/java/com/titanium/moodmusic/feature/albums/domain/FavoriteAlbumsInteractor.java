package com.titanium.moodmusic.feature.albums.domain;

import com.titanium.moodmusic.component.database.dao.MusicDao;
import com.titanium.moodmusic.component.database.entity.FavoriteAlbumTable;
import com.titanium.moodmusic.feature.albums.data.FavoriteAlbum;

import java.util.List;

import io.reactivex.Single;

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
