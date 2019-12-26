package com.titanium.moodmusic.feature.album.detail.di;

import com.titanium.moodmusic.app.di.scopes.AlbumsFeatureScope;
import com.titanium.moodmusic.feature.album.detail.ui.AlbumDetailsFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {AlbumDetailsModule.class})
@AlbumsFeatureScope
public interface AlbumDetailsSubComponent {

    void inject(AlbumDetailsFragment fragment);

    @Subcomponent.Builder
    interface Builder {

        Builder albumDetailsModule(AlbumDetailsModule module);

        AlbumDetailsSubComponent build();
    }
}
