package com.titanium.moodmusic.feature.tracks.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.titanium.moodmusic.component.network.Constants;
import com.titanium.moodmusic.feature.tracks.data.model.Track;
import com.titanium.moodmusic.feature.tracks.domain.ITracksInteractor;
import com.titanium.moodmusic.feature.tracks.presentation.ITracksPresenter;
import com.titanium.moodmusic.feature.tracks.presentation.ITracksView;
import com.titanium.moodmusic.feature.tracks.domain.TracksInteractor;
import com.titanium.moodmusic.feature.tracks.presentation.TracksPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class TracksPresenterModule {

    ITracksView iTracksView;

    public TracksPresenterModule(ITracksView iTracksView) {
        this.iTracksView = iTracksView;
    }

    @Singleton
    @Provides
    public ITracksPresenter providesTracksPresenter(ITracksView iTracksView, ITracksInteractor iTracksInteractor){
        return new TracksPresenter(iTracksView,iTracksInteractor);
    }

    @Singleton
    @Provides
    public ITracksView providesTracksView(){
        return this.iTracksView;
    }

    @Singleton
    @Provides
    public ITracksInteractor providesTracksInteractor(Retrofit retrofit){
        return new TracksInteractor(retrofit);
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
    public Converter.Factory providesConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    public CallAdapter.Factory providesAdapterRxFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public Gson providesGson(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Track.class, new Track.ArtistStateDeserializer())
                .create();
        return gson;
    }











}
