package com.titanium.moodmusic.app.di.modules;

import android.content.Context;

import com.titanium.moodmusic.app.di.scopes.ApplicationScope;
import com.titanium.moodmusic.component.network.ApiConfiguration;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiConfigurationModule {

    @Provides
    @ApplicationScope
    ApiConfiguration providesApiConfiguration(Context context) {
        return new ApiConfiguration(context);
    }
}
