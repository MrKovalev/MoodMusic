package com.titanium.moodmusic.feature.tracks.di;

import com.titanium.moodmusic.feature.tracks.ui.TracksFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TracksPresenterModule.class, TracksAdapterModule.class})
public interface TracksComponent {
    void inject(TracksFragment fragment);
}
