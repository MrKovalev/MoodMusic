package com.titanium.moodmusic.feature.albums.di;

import android.app.Application;

import com.titanium.moodmusic.component.database.dao.MusicDao;
import com.titanium.moodmusic.component.database.MusicDatabase;
import com.titanium.moodmusic.di.AppModule;
import com.titanium.moodmusic.feature.albums.ui.FavoriteAlbumsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FavoriteAlbumsAdapterModule.class, AppModule.class, FavoriteAlbumsPresenterModule.class})
public interface FavoriteAlbumsComponent {
    void inject(FavoriteAlbumsFragment fragment);

    Application application();
    MusicDao musicDao();
    MusicDatabase musicDatabase();
}
