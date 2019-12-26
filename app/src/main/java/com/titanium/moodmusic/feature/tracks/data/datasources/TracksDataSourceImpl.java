package com.titanium.moodmusic.feature.tracks.data.datasources;

import com.titanium.moodmusic.feature.tracks.data.api.TracksApi;
import com.titanium.moodmusic.feature.tracks.data.model.chart.ChartTrackModel;
import com.titanium.moodmusic.feature.tracks.data.model.search.SearchTrackModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class TracksDataSourceImpl implements TracksDataSource {

    private final TracksApi tracksApi;

    @Inject
    public TracksDataSourceImpl(TracksApi tracksApi) {
        this.tracksApi = tracksApi;
    }

    @Override
    public Single<List<ChartTrackModel>> getTopChartTracks(int page, int limit) {
        return tracksApi.getTopChartTracks(page, limit).map(
                topChartTracksResponce -> topChartTracksResponce.getTracksResponce().getChartTrackModels()
        );
    }

    @Override
    public Single<List<SearchTrackModel>> searchTrack(int limit, int page, String nameTrack, String nameArtist) {
        return tracksApi.searchTrack(limit, page, nameTrack, nameArtist).map(searchTrackResponce -> {
            if (searchTrackResponce.getTrackListResponce() != null) {
                return searchTrackResponce.getTrackListResponce().getTrackListMatches().getSearchTrackModels();
            } else {
                return new ArrayList<>();
            }
        });
    }

    @Override
    public Single<List<SearchTrackModel>> searchTracksByArtist(String nameArtist, String mbid, int limit, int page) {
        return tracksApi.searchTracksByArtist(nameArtist, mbid, limit, page).map(tracksByArtistResponce -> {
            if (tracksByArtistResponce.getTracksByArtistResponce() != null) {
                return tracksByArtistResponce.getTracksByArtistResponce().getSearchTrackModels();
            } else {
                return new ArrayList<>();
            }
        });
    }
}
