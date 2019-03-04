package com.titanium.moodmusic.ui.fragments.artists;

import com.titanium.moodmusic.data.model.responces.SearchArtistResponce;
import com.titanium.moodmusic.data.model.responces.TopChartArtistsResponce;
import com.titanium.moodmusic.network.LastFmArtistsCallsAPI;

import retrofit2.Call;
import retrofit2.Retrofit;

public class ArtistsInteractor implements IArtistsInteractor {
    private Retrofit retrofit;

    public ArtistsInteractor(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Call<TopChartArtistsResponce> getTopChartArtists(int page, int limit, String apiKey) {
        return retrofit.create(LastFmArtistsCallsAPI.class).getTopChartArtists(page,limit,apiKey);
    }

    @Override
    public Call<SearchArtistResponce> searchArtist(int page, int limit, String name, String apiKey) {
        return retrofit.create(LastFmArtistsCallsAPI.class).searchArtist(page, limit, name, apiKey);
    }
}
