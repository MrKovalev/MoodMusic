package com.titanium.moodmusic.ui.fragments.tracks;

import com.titanium.moodmusic.data.model.responces.SearchTrackResponce;
import com.titanium.moodmusic.data.model.responces.TopChartTracksResponce;
import com.titanium.moodmusic.data.model.responces.TracksByArtistResponce;
import com.titanium.moodmusic.network.LastFmAPI;
import com.titanium.moodmusic.ui.fragments.artists.IArtistsInteractor;

import retrofit2.Call;

public class TracksInteractor implements ITracksInteractor {

    private LastFmAPI lastFmAPI;

    public TracksInteractor(LastFmAPI lastFmAPI) {
        this.lastFmAPI = lastFmAPI;
    }

    @Override
    public Call<TopChartTracksResponce> getTopChartTracks(int page, int limit, String apiKey) {
        return lastFmAPI.getTopChartTracks(page,limit,apiKey);
    }

    @Override
    public Call<SearchTrackResponce> searchTrack(int limit, int page, String nameTrack, String nameArtist, String apiKey) {
        return lastFmAPI.searchTrack(limit,page,nameTrack,nameArtist,apiKey);
    }

    @Override
    public Call<TracksByArtistResponce> searchTracksByArtist(String nameArtist, String mbid, int limit, int page, String apiKey) {
        return lastFmAPI.searchTracksByArtist(nameArtist,mbid,limit,page,apiKey);
    }
}
