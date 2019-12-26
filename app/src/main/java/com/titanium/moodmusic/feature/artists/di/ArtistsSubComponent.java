package com.titanium.moodmusic.feature.artists.di;

import com.titanium.moodmusic.app.di.scopes.ArtistsFeatureScope;
import com.titanium.moodmusic.feature.artists.ui.ArtistsFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {ArtistsModule.class})
@ArtistsFeatureScope
public interface ArtistsSubComponent {

    void inject(ArtistsFragment fragment);

    @Subcomponent.Builder
    interface Builder {

        ArtistsSubComponent build();
    }
}
