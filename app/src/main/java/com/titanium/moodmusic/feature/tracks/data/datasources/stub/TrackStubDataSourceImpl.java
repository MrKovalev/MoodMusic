package com.titanium.moodmusic.feature.tracks.data.datasources.stub;

import com.titanium.moodmusic.feature.tracks.data.datasources.TracksDataSource;
import com.titanium.moodmusic.feature.tracks.data.model.chart.ArtistModel;
import com.titanium.moodmusic.feature.tracks.data.model.chart.ChartTrackModel;
import com.titanium.moodmusic.feature.tracks.data.model.search.SearchTrackModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class TrackStubDataSourceImpl implements TracksDataSource {

    @Inject
    public TrackStubDataSourceImpl() {
    }

    @Override
    public Single<List<ChartTrackModel>> getTopChartTracks(int page, int limit) {
        final List<ChartTrackModel> items = new ArrayList<>();
        items.add(new ChartTrackModel("123", "Decadance1", "10", "http", new ArtistModel("123", "Disturbed1", "http1")));
        items.add(new ChartTrackModel("123", "Decadance2", "10", "http", new ArtistModel("124", "Disturbed2", "http2")));
        items.add(new ChartTrackModel("123", "Decadance3", "10", "http", new ArtistModel("125", "Disturbed3", "http3")));
        items.add(new ChartTrackModel("123", "Decadance4", "10", "http", new ArtistModel("126", "Disturbed4", "http4")));
        items.add(new ChartTrackModel("123", "Decadance5", "10", "http", new ArtistModel("127", "Disturbed5", "http5")));

        return Single.just(items);
    }

    @Override
    public Single<List<SearchTrackModel>> searchTracksByArtist(String nameArtist, String mbid, int limit, int page) {
        final List<SearchTrackModel> items = new ArrayList<>();
        items.add(new SearchTrackModel("123", "Decadance1", "10", "http", "Disturbed"));
        items.add(new SearchTrackModel("123", "Decadance2", "10", "http", "Disturbed"));
        items.add(new SearchTrackModel("123", "Decadance3", "10", "http", "Disturbed"));
        items.add(new SearchTrackModel("123", "Decadance4", "10", "http", "Disturbed"));
        items.add(new SearchTrackModel("123", "Decadance5", "10", "http", "Disturbed"));
        items.add(new SearchTrackModel("123", "Decadance6", "10", "http", "Disturbed"));
        items.add(new SearchTrackModel("123", "Decadance7", "10", "http", "Disturbed"));

        return Single.just(items);
    }

    @Override
    public Single<List<SearchTrackModel>> searchTrack(int limit, int page, String nameTrack, String nameArtist) {
        final List<SearchTrackModel> items = new ArrayList<>();
        items.add(new SearchTrackModel("123", "Decadance1", "10", "http", "Disturbed"));
        items.add(new SearchTrackModel("123", "Decadance1", "10", "http", "Disturbed"));
        items.add(new SearchTrackModel("123", "Decadance3", "10", "http", "Disturbed"));
        items.add(new SearchTrackModel("123", "Decadance4", "10", "http", "Disturbed"));
        items.add(new SearchTrackModel("123", "Decadance5", "10", "http", "Disturbed"));
        items.add(new SearchTrackModel("123", "Decadance6", "10", "http", "Disturbed"));
        items.add(new SearchTrackModel("123", "Decadance7", "10", "http", "Disturbed"));

        return Single.just(items);
    }
}
