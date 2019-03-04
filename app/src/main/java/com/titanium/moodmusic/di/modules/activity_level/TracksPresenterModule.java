package com.titanium.moodmusic.di.modules.activity_level;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.titanium.moodmusic.data.api.Constants;
import com.titanium.moodmusic.data.model.tracks.Track;
import com.titanium.moodmusic.ui.fragments.tracks.ITracksInteractor;
import com.titanium.moodmusic.ui.fragments.tracks.ITracksPresenter;
import com.titanium.moodmusic.ui.fragments.tracks.ITracksView;
import com.titanium.moodmusic.ui.fragments.tracks.TracksInteractor;
import com.titanium.moodmusic.ui.fragments.tracks.TracksPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
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
    public Retrofit providesRetrofit(Converter.Factory converter){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(converter)
                .build();
    }

    @Singleton
    @Provides
    public Converter.Factory providesConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
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
