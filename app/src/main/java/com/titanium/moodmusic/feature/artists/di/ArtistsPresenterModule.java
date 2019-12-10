package com.titanium.moodmusic.feature.artists.di;

import com.titanium.moodmusic.component.network.Constants;
import com.titanium.moodmusic.feature.artists.domain.ArtistsInteractor;
import com.titanium.moodmusic.feature.artists.presentation.ArtistsPresenter;
import com.titanium.moodmusic.feature.artists.domain.IArtistsInteractor;
import com.titanium.moodmusic.feature.artists.presentation.IArtistsPresenter;
import com.titanium.moodmusic.feature.artists.presentation.IArtistsView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ArtistsPresenterModule {
    IArtistsView iArtistsView;

    public ArtistsPresenterModule(IArtistsView iArtistsView) {
        this.iArtistsView = iArtistsView;
    }

    @Singleton
    @Provides
    public IArtistsPresenter providesArtistsPresenter(IArtistsView view, IArtistsInteractor interactor){
        return new ArtistsPresenter(view,interactor);
    }

    @Singleton
    @Provides
    public IArtistsView providesArtistsView(){
        return this.iArtistsView;
    }

    @Singleton
    @Provides
    public IArtistsInteractor providesArtistsInteractor(Retrofit retrofit){
        return new ArtistsInteractor(retrofit);
    }

    @Singleton
    @Provides
    public Retrofit providesRetrofit(Converter.Factory converter, CallAdapter.Factory rxConverter){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(converter)
                .addCallAdapterFactory(rxConverter)
                .build();
    }

    @Singleton
    @Provides
    public Converter.Factory providesConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    public CallAdapter.Factory providesCallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }
}
