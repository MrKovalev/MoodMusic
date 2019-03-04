package com.titanium.moodmusic.ui.fragments.tracks;

import com.titanium.moodmusic.data.model.responces.SearchTrackResponce;
import com.titanium.moodmusic.data.model.responces.TopChartTracksResponce;
import com.titanium.moodmusic.data.model.responces.TracksByArtistResponce;
import com.titanium.moodmusic.network.LastFmArtistsCallsAPI;
import com.titanium.moodmusic.network.LastFmTracksCallsAPI;

import retrofit2.Call;
import retrofit2.Retrofit;

public class TracksInteractor implements ITracksInteractor {

    private Retrofit retrofit;

    public TracksInteractor(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Call<TopChartTracksResponce> getTopChartTracks(int page, int limit, String apiKey) {
        return retrofit.create(LastFmTracksCallsAPI.class).getTopChartTracks(page,limit,apiKey);
    }

    @Override
    public Call<SearchTrackResponce> searchTrack(int limit, int page, String nameTrack, String nameArtist, String apiKey) {
        return retrofit.create(LastFmTracksCallsAPI.class).searchTrack(limit,page,nameTrack,nameArtist,apiKey);
    }

    @Override
    public Call<TracksByArtistResponce> searchTracksByArtist(String nameArtist, String mbid, int limit, int page, String apiKey) {
        return retrofit.create(LastFmTracksCallsAPI.class).searchTracksByArtist(nameArtist,mbid,limit,page,apiKey);
    }
}
