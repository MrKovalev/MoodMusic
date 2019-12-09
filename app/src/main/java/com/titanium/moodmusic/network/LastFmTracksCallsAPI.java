package com.titanium.moodmusic.network;

import com.titanium.moodmusic.data.api.Constants;
import com.titanium.moodmusic.data.model.responces.SearchTrackResponce;
import com.titanium.moodmusic.data.model.responces.TopChartTracksResponce;
import com.titanium.moodmusic.data.model.responces.TracksByArtistResponce;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFmTracksCallsAPI {

    @GET(Constants.Endpoint.TOP_CHART_TRACKS_SEARCH)
    Single<TopChartTracksResponce> getTopChartTracks(
            @Query("page") int page,@Query("limit") int limit, @Query("api_key") String apiKey);

    @GET(Constants.Endpoint.SEARCH_TRACK)
    Single<SearchTrackResponce> searchTrack(
            @Query("limit") int limit, @Query("page") int page, @Query("track") String nameTrack,
            @Query("artist") String nameArtist, @Query("api_key") String apiKey);

    @GET(Constants.Endpoint.SEARCH_TRACKS_BY_ARTIST)
    Single<TracksByArtistResponce> searchTracksByArtist(
            @Query("artist") String nameArtist, @Query("mbid") String  mbid,
            @Query("limit") int limit, @Query("page") int page,
            @Query("api_key") String apiKey);
}
