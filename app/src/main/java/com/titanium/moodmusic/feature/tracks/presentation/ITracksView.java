package com.titanium.moodmusic.feature.tracks.presentation;

import com.titanium.moodmusic.feature.tracks.data.model.Track;

import java.util.List;

public interface ITracksView {
    void showProgress();
    void hideProgress();

    void loadTracks(List<Track> trackList);
    void searchTracks(List<Track> trackList);
    void searchTracksByArtist(List<Track> trackList);

    void openTrackDetail(List<Track> trackList, Track track, int position);

    void showError();
}
