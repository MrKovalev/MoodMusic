package com.titanium.moodmusic.ui.fragments.tracks;

import com.titanium.moodmusic.data.model.responces.SearchTrackResponce;
import com.titanium.moodmusic.data.model.responces.TopChartTracksResponce;
import com.titanium.moodmusic.data.model.responces.TracksByArtistResponce;

import io.reactivex.Single;

public interface ITracksInteractor {
    Single<TopChartTracksResponce> getTopChartTracks(int page, int limit, String apiKey);
    Single<SearchTrackResponce> searchTrack(int limit, int page, String nameTrack, String nameArtist, String apiKey);
    Single<TracksByArtistResponce> searchTracksByArtist(String nameArtist, String mbid, int limit, int page, String apiKey);
}
