package com.titanium.moodmusic.feature.albums.presentation;

import com.titanium.moodmusic.feature.albums.data.FavoriteAlbum;

import java.util.List;

public interface IFavoriteAlbumsView {
    void loadAlbums(List<FavoriteAlbum> albumList);
    void addAlbum(FavoriteAlbum favoriteAlbum);
    void editAlbum(FavoriteAlbum favoriteAlbum);
    void deleteAlbum(int position);
}
