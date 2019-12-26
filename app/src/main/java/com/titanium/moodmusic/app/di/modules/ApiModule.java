package com.titanium.moodmusic.app.di.modules;

import com.titanium.moodmusic.app.di.scopes.ApplicationScope;
import com.titanium.moodmusic.feature.artists.data.api.ArtistsApi;
import com.titanium.moodmusic.feature.tracks.data.api.TracksApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {RetrofitModule.class, ApiConfigurationModule.class})
public class ApiModule {

    @Provides
    @ApplicationScope
    ArtistsApi provideArtistsApi(Retrofit retrofit) {
        return retrofit.create(ArtistsApi.class);
    }

    @Provides
    @ApplicationScope
    TracksApi provideTracksApi(Retrofit retrofit) {
        return retrofit.create(TracksApi.class);
    }
}
