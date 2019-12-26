package com.titanium.moodmusic.feature.artists.data.api;

import com.titanium.moodmusic.feature.artists.data.model.responces.SearchArtistResponce;
import com.titanium.moodmusic.feature.artists.data.model.responces.TopChartArtistsResponce;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArtistsApi {

    @GET("?method=chart.gettopartists")
    Single<TopChartArtistsResponce> getTopChartArtists(
            @Query("page") int page, @Query("limit") int limit);

    @GET("?method=artist.search")
    Single<SearchArtistResponce> searchArtist(
            @Query("page") int page, @Query("limit") int limit,
            @Query("artist") String nameArtist);
}
