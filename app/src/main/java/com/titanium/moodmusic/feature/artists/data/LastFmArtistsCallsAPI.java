package com.titanium.moodmusic.feature.artists.data;

import com.titanium.moodmusic.component.network.Constants;
import com.titanium.moodmusic.feature.artists.data.model.SearchArtistResponce;
import com.titanium.moodmusic.feature.artists.data.model.TopChartArtistsResponce;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFmArtistsCallsAPI {

    @GET(Constants.Endpoint.TOP_CHART_ARTISTS_SEARCH)
    Single<TopChartArtistsResponce> getTopChartArtists(
            @Query("page") int page,@Query("limit") int limit, @Query("api_key") String apiKey);

    @GET(Constants.Endpoint.SEARCH_ARTIST)
    Single<SearchArtistResponce> searchArtist(
            @Query("page") int page,@Query("limit") int limit,
            @Query("artist") String nameArtist,@Query("api_key") String apiKey);
}
