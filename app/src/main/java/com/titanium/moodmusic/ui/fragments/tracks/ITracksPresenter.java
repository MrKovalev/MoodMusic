package com.titanium.moodmusic.ui.fragments.tracks;

import com.titanium.moodmusic.data.model.tracks.Track;

import java.util.List;

public interface ITracksPresenter {
    void onDestroy();

    void getTopChartTracks(int page,int limit, String apiKey);
    void searchTrack(int limit, int page, String nameTrack, String nameArtist, String apiKey);
    void searchTracksByArtist(String nameArtist, String mbid, int limit, int page, String apiKey);

    void openTrackDetail(List<Track> trackList, Track track, int position);
}
