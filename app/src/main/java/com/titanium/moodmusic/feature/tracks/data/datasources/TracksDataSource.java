package com.titanium.moodmusic.feature.tracks.data.datasources;

import com.titanium.moodmusic.feature.tracks.data.model.chart.ChartTrackModel;
import com.titanium.moodmusic.feature.tracks.data.model.search.SearchTrackModel;

import java.util.List;

import io.reactivex.Single;

public interface TracksDataSource {

    Single<List<ChartTrackModel>> getTopChartTracks(int page, int limit);

    Single<List<SearchTrackModel>> searchTrack(int limit, int page, String nameTrack, String nameArtist);

    Single<List<SearchTrackModel>> searchTracksByArtist(String nameArtist, String mbid, int limit, int page);
}
