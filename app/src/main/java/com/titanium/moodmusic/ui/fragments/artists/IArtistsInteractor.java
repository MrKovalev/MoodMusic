package com.titanium.moodmusic.ui.fragments.artists;

import com.titanium.moodmusic.data.model.responces.SearchArtistResponce;
import com.titanium.moodmusic.data.model.responces.TopChartArtistsResponce;

import retrofit2.Call;

public interface IArtistsInteractor {
    Call<TopChartArtistsResponce> getTopChartArtists(int page, int limit, String apiKey);
    Call<SearchArtistResponce> searchArtist(int page, int limit, String name, String apiKey);
}
