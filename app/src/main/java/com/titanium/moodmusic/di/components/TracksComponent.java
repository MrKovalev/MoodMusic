package com.titanium.moodmusic.di.components;

import com.titanium.moodmusic.di.modules.activity_level.TracksAdapterModule;
import com.titanium.moodmusic.di.modules.activity_level.TracksPresenterModule;
import com.titanium.moodmusic.ui.fragments.tracks.TracksFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TracksPresenterModule.class, TracksAdapterModule.class})
public interface TracksComponent {
    void inject(TracksFragment fragment);
}
