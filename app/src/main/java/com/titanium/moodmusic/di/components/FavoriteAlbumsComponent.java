package com.titanium.moodmusic.di.components;

import android.app.Application;

import com.titanium.moodmusic.data.db.dao.MusicDao;
import com.titanium.moodmusic.data.db.database.MusicDatabase;
import com.titanium.moodmusic.di.modules.activity_level.FavoriteAlbumsAdapterModule;
import com.titanium.moodmusic.di.modules.activity_level.FavoriteAlbumsPresenterModule;
import com.titanium.moodmusic.di.modules.app_level.AppModule;
import com.titanium.moodmusic.ui.fragments.favoriteAlbums.FavoriteAlbumsFragment;

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
