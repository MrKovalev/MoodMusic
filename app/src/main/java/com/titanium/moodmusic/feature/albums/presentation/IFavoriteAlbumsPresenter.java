package com.titanium.moodmusic.feature.albums.presentation;

import com.titanium.moodmusic.feature.albums.data.FavoriteAlbum;

import java.util.List;

public interface IFavoriteAlbumsPresenter {
    void onDestroy();

    void getFavoriteAlbums();
    void searchAlbum(String nameAlbum);
    void saveAlbumsToDatabase(List<FavoriteAlbum> favoriteAlbumList);

    void addFavoriteAlbum(FavoriteAlbum favoriteAlbum);
    void editFavoriteAlbum(FavoriteAlbum favoriteAlbum);
    void deleteFavoriteAlbum(int position);
}
