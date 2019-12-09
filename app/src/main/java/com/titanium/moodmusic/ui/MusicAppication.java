package com.titanium.moodmusic.ui;

import android.app.Application;

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
