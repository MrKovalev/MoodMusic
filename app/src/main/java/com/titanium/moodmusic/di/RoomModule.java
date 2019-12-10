package com.titanium.moodmusic.di;

import android.app.Application;

import androidx.room.Room;

import com.titanium.moodmusic.component.database.dao.MusicDao;
import com.titanium.moodmusic.component.database.MusicDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private MusicDatabase musicDatabase;

    public RoomModule(Application application) {
        musicDatabase = Room.databaseBuilder(application, MusicDatabase.class, "musicDatabase")
                .build();
    }

    @Singleton
    @Provides
    public MusicDao providesMusicDao(MusicDatabase musicDatabase){
        return musicDatabase.musicDao();
    }

    @Singleton
    @Provides
    public MusicDatabase providesMusicDatabase(){
        return musicDatabase;
    }
}
