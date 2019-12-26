package com.titanium.moodmusic.component.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.titanium.moodmusic.component.database.dao.AlbumsDao;
import com.titanium.moodmusic.component.database.entity.AlbumEntity;
import com.titanium.moodmusic.component.database.typeconverters.TrackTypeConverter;

@Database(entities = {AlbumEntity.class}, version = 1, exportSchema = false)
@TypeConverters(TrackTypeConverter.class)
public abstract class MusicDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "MUSIC_DB";

    public abstract AlbumsDao musicDao();
}