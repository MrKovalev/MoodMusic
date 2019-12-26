package com.titanium.moodmusic.feature.tracks.domain.interactors;

import com.titanium.moodmusic.shared.tracks.Track;

import java.util.List;

import io.reactivex.Single;

public interface TracksInteractor {

    Single<List<Track>> getTopChartTracks(int page, int limit);

    Single<List<Track>> searchTrack(int limit, int page, String nameTrack, String nameArtist);

    Single<List<Track>> searchTracksByArtist(String nameArtist, String mbid, int limit, int page);
}
