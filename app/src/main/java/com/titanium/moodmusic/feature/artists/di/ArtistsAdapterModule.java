package com.titanium.moodmusic.feature.artists.di;

import android.content.Context;

import com.titanium.moodmusic.feature.artists.ui.ArtistsAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ArtistsAdapterModule {
    private Context context;

    public ArtistsAdapterModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public ArtistsAdapter providesArtistsAdapter(){
        return new ArtistsAdapter(context);
    }
}
