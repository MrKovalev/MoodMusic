package com.titanium.moodmusic.di.modules.app_level;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.titanium.moodmusic.data.db.dao.MusicDao;
import com.titanium.moodmusic.data.db.database.MusicDatabase;

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
