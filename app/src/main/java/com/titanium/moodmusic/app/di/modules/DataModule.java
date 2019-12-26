package com.titanium.moodmusic.app.di.modules;

import com.titanium.moodmusic.component.resource.di.ResourceModule;
import com.titanium.moodmusic.shared.albums.di.AlbumsDataModule;
import com.titanium.moodmusic.shared.search.di.SearchDataModule;

import dagger.Module;

@Module(includes = {
        RoomModule.class,
        ApiModule.class,
        ResourceModule.class,
        AlbumsDataModule.class,
        SearchDataModule.class
})
public interface DataModule {
}
