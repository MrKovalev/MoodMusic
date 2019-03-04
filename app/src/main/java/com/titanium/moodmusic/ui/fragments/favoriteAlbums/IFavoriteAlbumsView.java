package com.titanium.moodmusic.ui.fragments.favoriteAlbums;

import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;

import java.util.List;

public interface IFavoriteAlbumsView {
    void loadAlbums(List<FavoriteAlbum> albumList);
    void addAlbum(FavoriteAlbum favoriteAlbum);
    void editAlbum(FavoriteAlbum favoriteAlbum);
    void deleteAlbum(int position);
}
