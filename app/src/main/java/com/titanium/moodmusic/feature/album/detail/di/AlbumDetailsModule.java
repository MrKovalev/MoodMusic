package com.titanium.moodmusic.feature.album.detail.di;

import android.os.Bundle;

import com.titanium.moodmusic.feature.album.detail.ui.AlbumDetailsFragment;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumDetailsModule {

    private final AlbumDetailsFragment fragment;

    public AlbumDetailsModule(AlbumDetailsFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Album providesAlbum(AlbumDetailsFragment fragment) {
        Bundle bundle = fragment.getArguments();

        if (bundle != null) {
            return (Album) bundle.get(AlbumDetailsFragment.EXTRA_ALBUM_KEY);
        }

        throw new IllegalArgumentException("Bundle must be not null!");
    }

    @Provides
    AlbumDetailsFragment providesFragment() {
        return fragment;
    }
}