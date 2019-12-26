package com.titanium.moodmusic.component.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.titanium.moodmusic.component.database.entity.AlbumEntity;
import com.titanium.moodmusic.shared.tracks.Track;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface AlbumsDao {

    @Query("select * from " + AlbumEntity.TABLE)
    Single<List<AlbumEntity>> getAllAlbums();

    @Query("select * from " + AlbumEntity.TABLE + " where " + AlbumEntity.ID + "= :id")
    Single<AlbumEntity> getAlbumById(long id);

    @Query("select * from " + AlbumEntity.TABLE + " where " + AlbumEntity.NAME_ALBUM + "= :name")
    Single<AlbumEntity> getAlbumByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAllAlbums(List<AlbumEntity> albumList);

    @Insert
    Completable insertAlbum(AlbumEntity album);

    @Query("update " + AlbumEntity.TABLE + " set tracks = :tracks where " + AlbumEntity.ID + "= :id")
    Completable updateAlbum(long id, List<Track> tracks);

    @Query("delete from " + AlbumEntity.TABLE)
    Completable deleteAllAlbums();

    @Query("delete from " + AlbumEntity.TABLE + " where " + AlbumEntity.NAME_ALBUM + "= :name")
    Completable deleteAlbumByName(String name);

    @Query("delete from " + AlbumEntity.TABLE + " where " + AlbumEntity.ID + "= :id")
    Completable deleteAlbumById(long id);

    @Delete
    Completable deleteAlbum(AlbumEntity albumEntity);
}
