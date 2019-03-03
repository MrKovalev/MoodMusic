package com.titanium.moodmusic.ui.fragments.favoriteAlbums;

import com.titanium.moodmusic.data.db.entity.FavoriteAlbumTable;
import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;

import java.util.List;


public interface IFavoriteAlbumsInteractor {
    List<FavoriteAlbumTable> getFavoriteAlbums();
    void saveAlbumsToDatabase(List<FavoriteAlbum> favoriteAlbums);
    List<FavoriteAlbum> searchAlbum(String nameAlbum);
}
