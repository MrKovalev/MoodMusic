package com.titanium.moodmusic.ui.fragments.favoriteAlbums;

import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;

import java.util.List;

public interface IFavoriteAlbumsView {
    void showProgress();
    void hideProgress();

    void loadAlbums(List<FavoriteAlbum> albumList);
    void searchAlbums(List<FavoriteAlbum> albumList);
    void addAlbum(FavoriteAlbum favoriteAlbum);
    void editAlbum(FavoriteAlbum favoriteAlbum);
    void deleteAlbum(int position);

    void showError();
    void showEmpty();
    void hideEmpty();
}
