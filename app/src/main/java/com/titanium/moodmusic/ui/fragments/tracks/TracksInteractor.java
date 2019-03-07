package com.titanium.moodmusic.ui.fragments.tracks;

import com.titanium.moodmusic.data.model.responces.SearchTrackResponce;
import com.titanium.moodmusic.data.model.responces.TopChartTracksResponce;
import com.titanium.moodmusic.data.model.responces.TracksByArtistResponce;
import com.titanium.moodmusic.network.LastFmArtistsCallsAPI;
import com.titanium.moodmusic.network.LastFmTracksCallsAPI;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Retrofit;

/** Класс отвечает за взаимодействие с данными, их получение из API, DB.. **/

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
