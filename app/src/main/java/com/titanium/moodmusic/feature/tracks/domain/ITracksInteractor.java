package com.titanium.moodmusic.feature.tracks.domain;

import com.titanium.moodmusic.feature.tracks.data.model.SearchTrackResponce;
import com.titanium.moodmusic.feature.tracks.data.model.TopChartTracksResponce;
import com.titanium.moodmusic.feature.tracks.data.model.TracksByArtistResponce;

import io.reactivex.Single;

public interface ITracksInteractor {
    Single<TopChartTracksResponce> getTopChartTracks(int page, int limit, String apiKey);
    Single<SearchTrackResponce> searchTrack(int limit, int page, String nameTrack, String nameArtist, String apiKey);
    Single<TracksByArtistResponce> searchTracksByArtist(String nameArtist, String mbid, int limit, int page, String apiKey);
}
