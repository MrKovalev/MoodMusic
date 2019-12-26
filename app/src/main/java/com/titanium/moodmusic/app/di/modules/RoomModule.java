package com.titanium.moodmusic.app.di.modules;

import android.app.Application;

import androidx.room.Room;

import com.titanium.moodmusic.app.di.scopes.ApplicationScope;
import com.titanium.moodmusic.component.database.MusicDatabase;
import com.titanium.moodmusic.component.database.dao.AlbumsDao;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    @ApplicationScope
    @Provides
    AlbumsDao providesMusicDao(MusicDatabase musicDatabase) {
        return musicDatabase.musicDao();
    }

    @ApplicationScope
    @Provides
    MusicDatabase providesMusicDatabase(Application application) {
        return Room.databaseBuilder(application, MusicDatabase.class, MusicDatabase.DATABASE_NAME)
                .build();
    }
}
