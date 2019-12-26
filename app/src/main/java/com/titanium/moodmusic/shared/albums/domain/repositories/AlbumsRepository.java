package com.titanium.moodmusic.shared.albums.domain.repositories;

import com.titanium.moodmusic.shared.albums.domain.entiries.Album;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface AlbumsRepository {

    Single<List<Album>> getFavoriteAlbums();

    Completable saveAlbums(List<Album> albums);

    Completable saveAlbum(Album album);

    Completable clearAlbums();

    Completable updateAlbumWithNewTracks(Album album);

    Completable deleteAlbum(int id);
}
