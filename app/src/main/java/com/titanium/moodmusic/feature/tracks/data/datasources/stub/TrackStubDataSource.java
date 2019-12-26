package com.titanium.moodmusic.feature.tracks.data.datasources.stub;

import com.titanium.moodmusic.feature.tracks.data.model.chart.ChartTrackModel;

import java.util.List;

import io.reactivex.Single;

public interface TrackStubDataSource {

    Single<List<ChartTrackModel>> getTopChartTracks(int page, int limit);

    Single<List<ChartTrackModel>> searchTrack(int limit, int page, String nameTrack, String nameArtist);

    Single<List<ChartTrackModel>> searchTracksByArtist(String nameArtist, String mbid, int limit, int page);
}
