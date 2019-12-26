package com.titanium.moodmusic.app.di.modules;

import com.titanium.moodmusic.app.di.components.MusicActivitySubComponent;
import com.titanium.moodmusic.shared.albums.di.AlbumsModule;
import com.titanium.moodmusic.shared.error.di.ErrorModule;
import com.titanium.moodmusic.shared.search.di.SearchModule;

import dagger.Module;

@Module(subcomponents = {MusicActivitySubComponent.class},
        includes = {
                ErrorModule.class,
                AlbumsModule.class,
                SearchModule.class
        })
public interface AppModule {
}