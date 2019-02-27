package com.titanium.moodmusic.ui.fragments.favoriteAlbums;

import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;

import java.util.List;


public interface IFavoriteAlbumsInteractor {
    List<FavoriteAlbum> getFavoriteAlbums();
    List<FavoriteAlbum> searchAlbum(String nameAlbum);
}
