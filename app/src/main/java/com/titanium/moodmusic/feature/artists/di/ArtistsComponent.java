package com.titanium.moodmusic.feature.artists.di;

import com.titanium.moodmusic.feature.artists.ui.ArtistsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ArtistsPresenterModule.class, ArtistsAdapterModule.class})
public interface ArtistsComponent {
    void inject(ArtistsFragment fragment);
}
