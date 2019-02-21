package com.titanium.moodmusic.ui.fragments.artists;

import com.titanium.moodmusic.data.model.Artist;
import com.titanium.moodmusic.data.model.ArtistsResponce;
import com.titanium.moodmusic.network.LastFmAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class ArtistsInteractor implements IArtistsInteractor {

    LastFmAPI lastFmAPI;

    public ArtistsInteractor(LastFmAPI lastFmAPI) {
        this.lastFmAPI = lastFmAPI;
    }

    @Override
    public Call<ArtistsResponce> getTopChartArtists(int limit, String apiKey) {
        return lastFmAPI.getTopChartArtists(limit,apiKey);
    }
}
