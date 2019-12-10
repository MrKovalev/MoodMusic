package com.titanium.moodmusic.component.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.titanium.moodmusic.component.database.entity.FavoriteAlbumTable;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface MusicDao {

    @Query("select * from favoritealbumtable")
    Single<List<FavoriteAlbumTable>> getAllAlbums();

    @Query("select * from favoritealbumtable where idAlbum = :id")
    FavoriteAlbumTable getAlbumById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllAlbums(List<FavoriteAlbumTable> albumList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlbum(FavoriteAlbumTable album);

    @Query("delete from favoritealbumtable")
    void deleteAllAlbums();

    @Delete
    void deleteAlbum(FavoriteAlbumTable favoriteAlbumTable);
}
