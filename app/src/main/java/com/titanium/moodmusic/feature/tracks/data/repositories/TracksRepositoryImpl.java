package com.titanium.moodmusic.feature.tracks.data.repositories;

import com.titanium.moodmusic.feature.tracks.data.converters.ChartTrackListConverter;
import com.titanium.moodmusic.feature.tracks.data.converters.SearchTrackListConverter;
import com.titanium.moodmusic.feature.tracks.data.datasources.TracksDataSource;
import com.titanium.moodmusic.feature.tracks.domain.repositories.TracksRepository;
import com.titanium.moodmusic.shared.tracks.Track;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Single;

public class TracksRepositoryImpl implements TracksRepository {

    private final TracksDataSource tracksDataSource;
    private final ChartTrackListConverter chartTrackListConverter;
    private final SearchTrackListConverter searchTrackListConverter;

    @Inject
    public TracksRepositoryImpl(TracksDataSource tracksDataSource, ChartTrackListConverter trackConverter, SearchTrackListConverter searchTrackListConverter) {
        this.tracksDataSource = tracksDataSource;
        this.chartTrackListConverter = trackConverter;
        this.searchTrackListConverter = searchTrackListConverter;
    }

    @Override
    public Single<List<Track>> getTopChartTracks(int page, int limit) {
        return tracksDataSource.getTopChartTracks(page, limit).map(chartTrackListConverter::convert);
    }

    @Override
    public Single<List<Track>> searchTrack(int limit, int page, String nameTrack, String nameArtist) {
        return tracksDataSource.searchTrack(limit, page, nameTrack, nameArtist).map(searchTrackListConverter::convert);
    }

    @Override
    public Single<List<Track>> searchTracksByArtist(String nameArtist, String mbid, int limit, int page) {
        return tracksDataSource.searchTracksByArtist(nameArtist, mbid, limit, page).map(searchTrackListConverter::convert);
    }
}
