package com.titanium.moodmusic.ui.fragments.favoriteAlbums;

import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;

public interface IFavoriteAlbumsPresenter {
    void getFavoriteAlbums();
    void searchAlbum(String nameAlbum);

    void addFavoriteAlbum(FavoriteAlbum favoriteAlbum);
    void editFavoriteAlbum(FavoriteAlbum favoriteAlbum);
    void deleteFavoriteAlbum(int position);
}
