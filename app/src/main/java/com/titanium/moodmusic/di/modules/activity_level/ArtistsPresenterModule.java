package com.titanium.moodmusic.di.modules.activity_level;

import com.titanium.moodmusic.data.api.Constants;
import com.titanium.moodmusic.ui.fragments.artists.ArtistsInteractor;
import com.titanium.moodmusic.ui.fragments.artists.ArtistsPresenter;
import com.titanium.moodmusic.ui.fragments.artists.IArtistsInteractor;
import com.titanium.moodmusic.ui.fragments.artists.IArtistsPresenter;
import com.titanium.moodmusic.ui.fragments.artists.IArtistsView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
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
    public Retrofit providesRetrofit(Converter.Factory converter){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(converter)
                .build();
    }

    @Singleton
    @Provides
    public Converter.Factory providesConverterFactory(){
        return GsonConverterFactory.create();
    }
}
