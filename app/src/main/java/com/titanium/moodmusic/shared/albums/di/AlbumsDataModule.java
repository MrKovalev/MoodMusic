package com.titanium.moodmusic.shared.albums.di;

import com.titanium.moodmusic.app.di.scopes.ApplicationScope;
import com.titanium.moodmusic.shared.albums.data.datasources.AlbumsDataSource;
import com.titanium.moodmusic.shared.albums.data.datasources.AlbumsDataSourceImpl;
import com.titanium.moodmusic.shared.albums.data.repositories.AlbumsRepositoryImpl;
import com.titanium.moodmusic.shared.albums.domain.repositories.AlbumsRepository;

import dagger.Binds;
import dagger.Module;

@Module
public interface AlbumsDataModule {

    @Binds
    @ApplicationScope
    AlbumsRepository bindsAlbumsRepository(AlbumsRepositoryImpl repository);

    @Binds
    @ApplicationScope
    AlbumsDataSource bindsAlbumsDataSource(AlbumsDataSourceImpl dataSource);
}
