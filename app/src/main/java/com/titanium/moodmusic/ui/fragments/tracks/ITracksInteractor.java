package com.titanium.moodmusic.ui.fragments.tracks;

import com.titanium.moodmusic.data.model.responces.SearchArtistResponce;
import com.titanium.moodmusic.data.model.responces.SearchTrackResponce;
import com.titanium.moodmusic.data.model.responces.TopChartArtistsResponce;
import com.titanium.moodmusic.data.model.responces.TopChartTracksResponce;
import com.titanium.moodmusic.data.model.responces.TracksByArtistResponce;

import retrofit2.Call;

public interface ITracksInteractor {
    Call<TopChartTracksResponce> getTopChartTracks(int page, int limit, String apiKey);
    Call<SearchTrackResponce> searchTrack(int limit, int page, String nameTrack, String nameArtist, String apiKey);
    Call<TracksByArtistResponce> searchTracksByArtist(String nameArtist, String mbid, int limit, int page, String apiKey);
}
