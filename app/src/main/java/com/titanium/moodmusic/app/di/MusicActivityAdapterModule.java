package com.titanium.moodmusic.app.di;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.titanium.moodmusic.component.ui.MainPagerAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MusicActivityAdapterModule {

    private Context context;
    private FragmentManager fragmentManager;

    public MusicActivityAdapterModule(FragmentManager fragmentManager, Context context) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Singleton
    @Provides
    public MainPagerAdapter providesMusicMainPagerAdapter() {
        return new MainPagerAdapter(fragmentManager, context);
    }
}
