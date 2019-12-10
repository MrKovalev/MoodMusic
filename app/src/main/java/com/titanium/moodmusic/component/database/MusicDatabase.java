package com.titanium.moodmusic.component.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.titanium.moodmusic.component.database.dao.MusicDao;
import com.titanium.moodmusic.component.database.entity.FavoriteAlbumTable;

@Database(entities = {FavoriteAlbumTable.class}, version = 1, exportSchema = false)
public abstract class MusicDatabase extends RoomDatabase {
    public abstract MusicDao musicDao();
}
