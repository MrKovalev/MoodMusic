package com.titanium.moodmusic.app.di.components;

import com.titanium.moodmusic.app.MainActivity;
import com.titanium.moodmusic.app.di.scopes.MusicActivityScope;

import dagger.Subcomponent;

@MusicActivityScope
@Subcomponent
public interface MusicActivitySubComponent {

    void inject(MainActivity activity);

    @Subcomponent.Builder
    interface Builder {

        MusicActivitySubComponent build();
    }
}