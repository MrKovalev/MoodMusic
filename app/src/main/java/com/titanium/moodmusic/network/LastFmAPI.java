package com.titanium.moodmusic.network;

import com.titanium.moodmusic.data.model.Artist;
import com.titanium.moodmusic.data.api.Constants;
import com.titanium.moodmusic.data.model.ArtistsResponce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFmAPI {

    @GET(Constants.Endpoint.TOP_CHART_ARTISTS_SEARCH)
    Call<ArtistsResponce> getTopChartArtists(
            @Query("limit") int limit, @Query("api_key") String apiKey);
}
