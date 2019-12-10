package com.titanium.moodmusic.feature.tracks.di;

import android.content.Context;

import com.titanium.moodmusic.feature.tracks.ui.TracksAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TracksAdapterModule {

    private Context context;

    public TracksAdapterModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public TracksAdapter providesTracksAdapter(){
        return new TracksAdapter(context);
    }
}
