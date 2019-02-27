package com.titanium.moodmusic.ui.fragments.tracks;

import com.titanium.moodmusic.data.model.tracks.Track;

import java.util.List;

public interface ITracksView {
    void showProgress();
    void hideProgress();

    void loadTracks(List<Track> trackList);
    void searchTracks(List<Track> trackList);
    void searchTracksByArtist(List<Track> trackList);

    void openTrackDetail(List<Track> trackList, Track track, int position);

    void showError();
    void showEmpty();
    void hideEmpty();
}
