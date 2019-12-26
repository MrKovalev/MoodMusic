package com.titanium.moodmusic.app.di.components;

import android.app.Application;
import android.content.Context;

import com.titanium.moodmusic.app.App;
import com.titanium.moodmusic.app.di.modules.AppModule;
import com.titanium.moodmusic.app.di.modules.DataModule;
import com.titanium.moodmusic.app.di.scopes.ApplicationScope;
import com.titanium.moodmusic.feature.album.detail.di.AlbumDetailsSubComponent;
import com.titanium.moodmusic.feature.albums.di.AlbumsSubComponent;
import com.titanium.moodmusic.feature.artists.di.ArtistsSubComponent;
import com.titanium.moodmusic.feature.tracks.di.TracksSubComponent;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {AppModule.class, DataModule.class})
@ApplicationScope
public interface AppComponent {

    void inject(App app);

    MusicActivitySubComponent.Builder musicActivityComponent();

    ArtistsSubComponent.Builder artistsFragmentSubComponentBuilder();

    TracksSubComponent.Builder tracksFragmentSubComponentBuilder();

    AlbumsSubComponent.Builder albumsFragmentSubComponentBuilder();

    AlbumDetailsSubComponent.Builder albumDetailsFragmentSubComponentBuilder();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder app(Application app);

        @BindsInstance
        Builder context(Context context);

        AppComponent build();
    }
}