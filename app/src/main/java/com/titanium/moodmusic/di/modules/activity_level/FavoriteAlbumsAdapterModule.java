package com.titanium.moodmusic.di.modules.activity_level;

import android.content.Context;

import com.titanium.moodmusic.ui.adapters.FavoriteAlbumsAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FavoriteAlbumsAdapterModule {
    private Context context;

    public FavoriteAlbumsAdapterModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public FavoriteAlbumsAdapter providesAlbumsAdapter(){
        return new FavoriteAlbumsAdapter(context);
    }
}
