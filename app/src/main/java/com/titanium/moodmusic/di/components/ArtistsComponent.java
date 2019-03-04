package com.titanium.moodmusic.di.components;

import com.titanium.moodmusic.ui.fragments.artists.ArtistsFragment;
import com.titanium.moodmusic.di.modules.activity_level.ArtistsAdapterModule;
import com.titanium.moodmusic.di.modules.activity_level.ArtistsPresenterModule;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {ArtistsPresenterModule.class, ArtistsAdapterModule.class})
public interface ArtistsComponent {
    void inject(ArtistsFragment fragment);
}
