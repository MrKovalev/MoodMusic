package com.titanium.moodmusic.feature.tracks.domain;

import com.titanium.moodmusic.feature.tracks.data.model.SearchTrackResponce;
import com.titanium.moodmusic.feature.tracks.data.model.TopChartTracksResponce;
import com.titanium.moodmusic.feature.tracks.data.model.TracksByArtistResponce;
import com.titanium.moodmusic.feature.tracks.data.LastFmTracksCallsAPI;

import io.reactivex.Single;
import retrofit2.Retrofit;

public class TracksInteractor implements ITracksInteractor {

    private Retrofit retrofit;

    public TracksInteractor(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Single<TopChartTracksResponce> getTopChartTracks(int page, int limit, String apiKey) {
        return retrofit.create(LastFmTracksCallsAPI.class).getTopChartTracks(page,limit,apiKey);
    }

    @Override
    public Single<SearchTrackResponce> searchTrack(int limit, int page, String nameTrack, String nameArtist, String apiKey) {
        return retrofit.create(LastFmTracksCallsAPI.class).searchTrack(limit,page,nameTrack,nameArtist,apiKey);
    }

    @Override
    public Single<TracksByArtistResponce> searchTracksByArtist(String nameArtist, String mbid, int limit, int page, String apiKey) {
        return retrofit.create(LastFmTracksCallsAPI.class).searchTracksByArtist(nameArtist,mbid,limit,page,apiKey);
    }
}
