package com.titanium.moodmusic.feature.albums.di;

import com.titanium.moodmusic.app.di.scopes.AlbumsFeatureScope;
import com.titanium.moodmusic.feature.albums.ui.AlbumsFragment;

import dagger.Subcomponent;

@Subcomponent
@AlbumsFeatureScope
public interface AlbumsSubComponent {

    void inject(AlbumsFragment fragment);

    @Subcomponent.Builder
    interface Builder {

        AlbumsSubComponent build();
    }
}
