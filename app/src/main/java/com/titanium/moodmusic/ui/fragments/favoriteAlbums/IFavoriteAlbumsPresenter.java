package com.titanium.moodmusic.ui.fragments.favoriteAlbums;

import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;

import java.util.List;

public interface IFavoriteAlbumsPresenter {
    void getFavoriteAlbums();
    void searchAlbum(String nameAlbum);
    void saveAlbumsToDatabase(List<FavoriteAlbum> favoriteAlbumList);

    void addFavoriteAlbum(FavoriteAlbum favoriteAlbum);
    void editFavoriteAlbum(FavoriteAlbum favoriteAlbum);
    void deleteFavoriteAlbum(int position);
}
