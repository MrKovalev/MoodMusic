package com.titanium.moodmusic.di.modules.activity_level;

import android.content.Context;

import com.titanium.moodmusic.ui.adapters.TracksAdapter;

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
