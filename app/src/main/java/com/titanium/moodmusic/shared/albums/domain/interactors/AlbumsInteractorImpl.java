package com.titanium.moodmusic.shared.albums.domain.interactors;

import com.titanium.moodmusic.shared.albums.domain.entiries.Album;
import com.titanium.moodmusic.shared.albums.domain.repositories.AlbumsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class AlbumsInteractorImpl implements AlbumsInteractor {

    private final AlbumsRepository repository;

    @Inject
    public AlbumsInteractorImpl(AlbumsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<List<Album>> getFavoriteAlbums() {
        return repository.getFavoriteAlbums();
    }

    @Override
    public Completable clearAlbums() {
        return repository.clearAlbums();
    }

    @Override
    public Completable saveAlbums(final List<Album> albums) {
        return repository.saveAlbums(albums);
    }

    @Override
    public Completable saveAlbum(Album album) {
        return repository.saveAlbum(album);
    }

    @Override
    public Completable updateAlbumWithNewTracks(Album album) {
        return repository.updateAlbumWithNewTracks(album);
    }

    @Override
    public Completable deleteAlbum(int id) {
        return repository.deleteAlbum(id);
    }
}
