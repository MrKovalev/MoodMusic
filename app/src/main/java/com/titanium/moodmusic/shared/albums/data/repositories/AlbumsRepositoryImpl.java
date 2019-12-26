package com.titanium.moodmusic.shared.albums.data.repositories;

import com.titanium.moodmusic.shared.albums.data.converters.AlbumDbConverter;
import com.titanium.moodmusic.shared.albums.data.converters.AlbumDbListConverter;
import com.titanium.moodmusic.shared.albums.data.converters.AlbumEntityListConverter;
import com.titanium.moodmusic.shared.albums.data.datasources.AlbumsDataSource;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;
import com.titanium.moodmusic.shared.albums.domain.repositories.AlbumsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class AlbumsRepositoryImpl implements AlbumsRepository {

    private final AlbumsDataSource albumsDataSource;
    private final AlbumDbConverter albumDbConverter;
    private final AlbumEntityListConverter albumEntityListConverter;
    private final AlbumDbListConverter albumDbListConverter;

    @Inject
    public AlbumsRepositoryImpl(AlbumsDataSource albumsDataSource, AlbumDbConverter albumDbConverter, AlbumEntityListConverter albumEntityListConverter, AlbumDbListConverter albumDbListConverter) {
        this.albumsDataSource = albumsDataSource;
        this.albumDbConverter = albumDbConverter;
        this.albumEntityListConverter = albumEntityListConverter;
        this.albumDbListConverter = albumDbListConverter;
    }

    @Override
    public Single<List<Album>> getFavoriteAlbums() {
        return albumsDataSource.getAlbums().map(albumEntityListConverter::convert);
    }

    @Override
    public Completable saveAlbums(List<Album> albums) {
        return albumsDataSource.saveAlbums(albumDbListConverter.convert(albums));
    }

    @Override
    public Completable saveAlbum(Album album) {
        return albumsDataSource.saveAlbum(albumDbConverter.convert(album));
    }

    @Override
    public Completable clearAlbums() {
        return albumsDataSource.clearAlbums();
    }

    @Override
    public Completable updateAlbumWithNewTracks(Album album) {
        return albumsDataSource.updateAlbumWithNewTracks(albumDbConverter.convert(album));
    }

    @Override
    public Completable deleteAlbum(int id) {
        return albumsDataSource.deleteAlbum(id);
    }
}