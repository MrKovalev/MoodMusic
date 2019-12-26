package com.titanium.moodmusic.shared.albums.di;

import com.titanium.moodmusic.shared.albums.domain.interactors.AlbumsInteractor;
import com.titanium.moodmusic.shared.albums.domain.interactors.AlbumsInteractorImpl;

import dagger.Binds;
import dagger.Module;

@Module
public interface AlbumsModule {

    @Binds
    AlbumsInteractor bindsFavoriteAlbumsInteractor(AlbumsInteractorImpl interactor);
}