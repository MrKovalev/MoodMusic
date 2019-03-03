package com.titanium.moodmusic.ui;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.titanium.moodmusic.data.db.database.MusicDatabase;

public class MusicAppication extends Application {
    public static MusicAppication instance;
    private MusicDatabase musicDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        musicDatabase = Room.databaseBuilder(this,MusicDatabase.class,"musicDatabase").build();
    }

    public static MusicAppication getInstance() {
        return instance;
    }

    public MusicDatabase getMusicDatabase() {
        return musicDatabase;
    }
}
