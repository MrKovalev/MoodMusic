package com.titanium.moodmusic.ui.fragments.artists;

import com.titanium.moodmusic.data.model.responces.SearchArtistResponce;
import com.titanium.moodmusic.data.model.responces.TopChartArtistsResponce;
import com.titanium.moodmusic.network.LastFmAPI;

import retrofit2.Call;

public class ArtistsInteractor implements IArtistsInteractor {

    LastFmAPI lastFmAPI;

    public ArtistsInteractor(LastFmAPI lastFmAPI) {
        this.lastFmAPI = lastFmAPI;
    }

    @Override
    public Call<TopChartArtistsResponce> getTopChartArtists(int page, int limit, String apiKey) {
        return lastFmAPI.getTopChartArtists(page,limit,apiKey);
    }

    @Override
    public Call<SearchArtistResponce> searchArtist(int page, int limit, String name, String apiKey) {
        return lastFmAPI.searchArtist(page, limit, name, apiKey);
    }
}
