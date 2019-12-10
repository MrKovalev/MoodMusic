package com.titanium.moodmusic.feature.artists.domain;

import com.titanium.moodmusic.feature.artists.data.model.SearchArtistResponce;
import com.titanium.moodmusic.feature.artists.data.model.TopChartArtistsResponce;
import com.titanium.moodmusic.feature.artists.data.LastFmArtistsCallsAPI;

import io.reactivex.Single;
import retrofit2.Retrofit;

public class ArtistsInteractor implements IArtistsInteractor {
    private Retrofit retrofit;

    public ArtistsInteractor(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Single<TopChartArtistsResponce> getTopChartArtists(int page, int limit, String apiKey) {
        return retrofit.create(LastFmArtistsCallsAPI.class).getTopChartArtists(page,limit,apiKey);
    }

    @Override
    public Single<SearchArtistResponce> searchArtist(int page, int limit, String name, String apiKey) {
        return retrofit.create(LastFmArtistsCallsAPI.class).searchArtist(page, limit, name, apiKey);
    }
}
