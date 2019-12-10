package com.titanium.moodmusic.app.di;

import com.titanium.moodmusic.app.MusicActivity;
import com.titanium.moodmusic.app.di.MusicActivityAdapterModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MusicActivityAdapterModule.class)
public interface MusicActivityComponent {
    void inject(MusicActivity activity);
}
