package com.titanium.moodmusic.ui;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.titanium.moodmusic.data.db.database.MusicDatabase;

public class MusicAppication extends Application {
    public static MusicAppication instance;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static MusicAppication getInstance() {
        return instance;
    }
}
