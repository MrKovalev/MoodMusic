package com.titanium.moodmusic.shared.albums.domain.interactors;

import com.titanium.moodmusic.shared.albums.domain.entiries.Album;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface AlbumsInteractor {

    Single<List<Album>> getFavoriteAlbums();

    Completable clearAlbums();

    Completable saveAlbums(List<Album> albums);

    Completable saveAlbum(Album album);

    Completable updateAlbumWithNewTracks(Album album);

    Completable deleteAlbum(int id);
}
