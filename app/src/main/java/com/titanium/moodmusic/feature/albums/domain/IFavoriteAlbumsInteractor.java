package com.titanium.moodmusic.feature.albums.domain;

import com.titanium.moodmusic.component.database.entity.FavoriteAlbumTable;
import com.titanium.moodmusic.feature.albums.data.FavoriteAlbum;

import java.util.List;

import io.reactivex.Single;


public interface IFavoriteAlbumsInteractor {
    Single<List<FavoriteAlbumTable>> getFavoriteAlbums();
    void saveAlbumsToDatabase(List<FavoriteAlbum> favoriteAlbums);
    List<FavoriteAlbum> searchAlbum(String nameAlbum);
}
