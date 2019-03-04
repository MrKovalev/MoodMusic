package com.titanium.moodmusic.network;

import com.titanium.moodmusic.data.api.Constants;
import com.titanium.moodmusic.data.model.responces.SearchArtistResponce;
import com.titanium.moodmusic.data.model.responces.SearchTrackResponce;
import com.titanium.moodmusic.data.model.responces.TopChartArtistsResponce;
import com.titanium.moodmusic.data.model.responces.TopChartTracksResponce;
import com.titanium.moodmusic.data.model.responces.TracksByArtistResponce;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFmArtistsCallsAPI {

    @GET(Constants.Endpoint.TOP_CHART_ARTISTS_SEARCH)
    Call<TopChartArtistsResponce> getTopChartArtists(
            @Query("page") int page,@Query("limit") int limit, @Query("api_key") String apiKey);

    @GET(Constants.Endpoint.SEARCH_ARTIST)
    Call<SearchArtistResponce> searchArtist(
            @Query("page") int page,@Query("limit") int limit,
            @Query("artist") String nameArtist,@Query("api_key") String apiKey);

}
