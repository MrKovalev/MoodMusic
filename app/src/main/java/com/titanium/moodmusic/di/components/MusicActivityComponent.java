package com.titanium.moodmusic.di.components;

import com.titanium.moodmusic.di.modules.activity_level.MusicActivityAdapterModule;
import com.titanium.moodmusic.ui.activities.MusicActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MusicActivityAdapterModule.class)
public interface MusicActivityComponent {
    void inject(MusicActivity activity);
}
