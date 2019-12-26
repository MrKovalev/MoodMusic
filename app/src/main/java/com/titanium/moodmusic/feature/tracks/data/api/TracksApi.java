package com.titanium.moodmusic.feature.tracks.data.api;

import com.titanium.moodmusic.feature.tracks.data.model.responces.SearchTrackByNameResponce;
import com.titanium.moodmusic.feature.tracks.data.model.responces.TopChartTracksResponce;
import com.titanium.moodmusic.feature.tracks.data.model.responces.SearchTracksByArtistResponce;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TracksApi {

    @GET("?method=chart.gettoptracks")
    Single<TopChartTracksResponce> getTopChartTracks(
            @Query("page") int page,@Query("limit") int limit);

    @GET("?method=track.search")
    Single<SearchTrackByNameResponce> searchTrack(
            @Query("limit") int limit, @Query("page") int page, @Query("track") String nameTrack,
            @Query("artist") String nameArtist);

    @GET("?method=artist.gettoptracks")
    Single<SearchTracksByArtistResponce> searchTracksByArtist(
            @Query("artist") String nameArtist, @Query("mbid") String  mbid,
            @Query("limit") int limit, @Query("page") int page);
}
