package com.titanium.moodmusic.feature.tracks.di;

import com.titanium.moodmusic.app.di.scopes.TracksFeatureScope;
import com.titanium.moodmusic.feature.tracks.ui.TracksFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {TracksModule.class})
@TracksFeatureScope
public interface TracksSubComponent {

    void inject(TracksFragment fragment);

    @Subcomponent.Builder
    interface Builder {

        TracksSubComponent build();
    }
}
