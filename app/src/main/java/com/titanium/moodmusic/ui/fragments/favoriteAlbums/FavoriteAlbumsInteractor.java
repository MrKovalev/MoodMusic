package com.titanium.moodmusic.ui.fragments.favoriteAlbums;

import android.util.Log;

import com.titanium.moodmusic.data.db.dao.MusicDao;
import com.titanium.moodmusic.data.db.entity.FavoriteAlbumTable;
import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;
import com.titanium.moodmusic.ui.fragments.artists.ArtistsGenerator;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAlbumsInteractor implements IFavoriteAlbumsInteractor {

    private MusicDao musicDao;

    public FavoriteAlbumsInteractor(MusicDao musicDao) {
        this.musicDao = musicDao;
    }

    @Override
    public List<FavoriteAlbumTable> getFavoriteAlbums() {
        return new ArrayList<FavoriteAlbumTable>();
        //return musicDao.getAllAlbums();
    }

    @Override
    public void saveAlbumsToDatabase(final List<FavoriteAlbum> favoriteAlbums) {
        Thread threadWriteData = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "SAVE TO DB IN ANOTHER THREAD");
                Log.d("TAG", "count data = "+favoriteAlbums.size());

                musicDao.deleteAllAlbums();

                for (FavoriteAlbum item : favoriteAlbums) {

                    Log.d("TAG", "count data list = "+item.getTrackList().size());

                    FavoriteAlbumTable favoriteAlbumTable = new FavoriteAlbumTable();
                    favoriteAlbumTable.setIdAlbum(item.getAlbumId());
                    favoriteAlbumTable.setNameAlbum(item.getNameAlbum());
                    favoriteAlbumTable.setCountSongsInAlbum(item.getCountSongsInAlbum());
                    favoriteAlbumTable.setTrackList(item.getTrackList());

                    musicDao.insertAlbum(favoriteAlbumTable);
                }
            }
        });

        threadWriteData.start();
    }

    @Override
    public List<FavoriteAlbum> searchAlbum(String nameAlbum) {
        return null;
    }
}
