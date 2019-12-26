package com.titanium.moodmusic.shared.albums.data.datasources;

import com.titanium.moodmusic.component.database.dao.AlbumsDao;
import com.titanium.moodmusic.component.database.entity.AlbumEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class AlbumsDataSourceImpl implements AlbumsDataSource {

    private final AlbumsDao albumsDao;

    @Inject
    public AlbumsDataSourceImpl(AlbumsDao albumsDao) {
        this.albumsDao = albumsDao;
    }

    @Override
    public Single<List<AlbumEntity>> getAlbums() {
        return albumsDao.getAllAlbums();
    }

    @Override
    public Completable saveAlbums(List<AlbumEntity> albumEntities) {
        return albumsDao.insertAllAlbums(albumEntities);
    }

    @Override
    public Completable saveAlbum(AlbumEntity albumEntity) {
        return albumsDao.insertAlbum(albumEntity);
    }

    @Override
    public Completable clearAlbums() {
        return albumsDao.deleteAllAlbums();
    }

    @Override
    public Completable updateAlbumWithNewTracks(AlbumEntity album) {
        return albumsDao.updateAlbum(album.getIdAlbum(), album.getTracks());
    }

    @Override
    public Completable deleteAlbum(int id) {
        return albumsDao.deleteAlbumById(id);
    }
}