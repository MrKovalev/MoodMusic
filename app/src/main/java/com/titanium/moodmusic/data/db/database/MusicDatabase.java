package com.titanium.moodmusic.data.db.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.titanium.moodmusic.data.db.dao.MusicDao;
import com.titanium.moodmusic.data.db.entity.FavoriteAlbumTable;

@Database(entities = {FavoriteAlbumTable.class}, version = 1, exportSchema = false)
public abstract class MusicDatabase extends RoomDatabase {
    public abstract MusicDao musicDao();
}
