package com.titanium.moodmusic.shared.albums.data.datasources;

import com.titanium.moodmusic.component.database.entity.AlbumEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface AlbumsDataSource {

    Single<List<AlbumEntity>> getAlbums();

    Completable saveAlbums(List<AlbumEntity> album);

    Completable saveAlbum(AlbumEntity album);

    Completable clearAlbums();

    Completable updateAlbumWithNewTracks(AlbumEntity album);

    Completable deleteAlbum(int id);
}
