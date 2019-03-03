package com.titanium.moodmusic.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.titanium.moodmusic.data.db.entity.FavoriteAlbumTable;
import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;

import java.util.List;

@Dao
public interface MusicDao {

    @Query("select * from favoritealbumtable")
    List<FavoriteAlbumTable> getAllAlbums();

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
