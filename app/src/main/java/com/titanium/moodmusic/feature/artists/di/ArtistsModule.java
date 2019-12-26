package com.titanium.moodmusic.feature.artists.di;

import com.titanium.moodmusic.BuildConfig;
import com.titanium.moodmusic.app.di.scopes.ArtistsFeatureScope;
import com.titanium.moodmusic.feature.artists.data.api.ArtistsApi;
import com.titanium.moodmusic.feature.artists.data.datasources.ArtistsDataSource;
import com.titanium.moodmusic.feature.artists.data.datasources.ArtistsDataSourceImpl;
import com.titanium.moodmusic.feature.artists.data.datasources.stub.ArtistsStubDataSourceImpl;
import com.titanium.moodmusic.feature.artists.data.repositories.ArtistsRepositoryImpl;
import com.titanium.moodmusic.feature.artists.domain.interactors.ArtistsInteractor;
import com.titanium.moodmusic.feature.artists.domain.interactors.ArtistsInteractorImpl;
import com.titanium.moodmusic.feature.artists.domain.repositories.ArtistsRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
abstract class ArtistsModule {

    @Binds
    abstract ArtistsInteractor bindsArtistsInteractor(ArtistsInteractorImpl interactor);

    @Binds
    @ArtistsFeatureScope
    abstract ArtistsRepository bindsArtistsRepository(ArtistsRepositoryImpl artistsRepository);

    @Provides
    @ArtistsFeatureScope
    static ArtistsDataSource providesArtistsDataSource(ArtistsApi artistsApi) {
        if (BuildConfig.STUB_ENABLED) {
            return new ArtistsStubDataSourceImpl();
        } else {
            return new ArtistsDataSourceImpl(artistsApi);
        }
    }
}
