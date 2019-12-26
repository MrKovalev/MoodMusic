package com.titanium.moodmusic.feature.tracks.di;

import com.titanium.moodmusic.BuildConfig;
import com.titanium.moodmusic.app.di.scopes.TracksFeatureScope;
import com.titanium.moodmusic.feature.tracks.data.api.TracksApi;
import com.titanium.moodmusic.feature.tracks.data.datasources.TracksDataSource;
import com.titanium.moodmusic.feature.tracks.data.datasources.TracksDataSourceImpl;
import com.titanium.moodmusic.feature.tracks.data.datasources.stub.TrackStubDataSourceImpl;
import com.titanium.moodmusic.feature.tracks.data.repositories.TracksRepositoryImpl;
import com.titanium.moodmusic.feature.tracks.domain.interactors.TracksInteractor;
import com.titanium.moodmusic.feature.tracks.domain.interactors.TracksInteractorImpl;
import com.titanium.moodmusic.feature.tracks.domain.repositories.TracksRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
abstract class TracksModule {

    @Binds
    abstract TracksInteractor bindsTracksInteractor(TracksInteractorImpl interactor);

    @Binds
    @TracksFeatureScope
    abstract TracksRepository bindsTracksRepository(TracksRepositoryImpl tracksRepository);

    @Provides
    @TracksFeatureScope
    static TracksDataSource providesTracksDataSource(TracksApi tracksApi) {
        if (BuildConfig.STUB_ENABLED) {
            return new TrackStubDataSourceImpl();
        } else {
            return new TracksDataSourceImpl(tracksApi);
        }
    }
}
