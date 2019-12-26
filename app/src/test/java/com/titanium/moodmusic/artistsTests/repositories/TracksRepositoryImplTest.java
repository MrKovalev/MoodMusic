package com.titanium.moodmusic.artistsTests.repositories;

import com.titanium.moodmusic.feature.tracks.data.converters.ChartTrackListConverter;
import com.titanium.moodmusic.feature.tracks.data.converters.SearchTrackListConverter;
import com.titanium.moodmusic.feature.tracks.data.datasources.TracksDataSource;
import com.titanium.moodmusic.feature.tracks.data.model.chart.ChartTrackModel;
import com.titanium.moodmusic.feature.tracks.data.model.search.SearchTrackModel;
import com.titanium.moodmusic.feature.tracks.data.repositories.TracksRepositoryImpl;
import com.titanium.moodmusic.feature.tracks.domain.repositories.TracksRepository;
import com.titanium.moodmusic.shared.tracks.Track;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TracksRepositoryImplTest {

    private TracksDataSource tracksDataSource = mock(TracksDataSource.class);
    private ChartTrackListConverter chartTrackListConverter = mock(ChartTrackListConverter.class);
    private SearchTrackListConverter searchTrackListConverter = mock(SearchTrackListConverter.class);

    private TracksRepository tracksRepository = new TracksRepositoryImpl(
            tracksDataSource,
            chartTrackListConverter,
            searchTrackListConverter);

    private final ChartTrackModel chartTrackModel = mock(ChartTrackModel.class);
    private final SearchTrackModel searchTrackModel = mock(SearchTrackModel.class);

    private final List<ChartTrackModel> chartTrackModels = Collections.singletonList(chartTrackModel);
    private final List<SearchTrackModel> searchTrackModels = Collections.singletonList(searchTrackModel);

    private final Track track = mock(Track.class);
    private final List<Track> tracks = Collections.singletonList(track);

    @Test
    public void whenGetTracksExpectLoadTracksFromDataSource() {
        when(tracksDataSource.getTopChartTracks(anyInt(), anyInt()))
                .thenReturn(Single.just(chartTrackModels));
        when(chartTrackListConverter.convert(chartTrackModels)).thenReturn(tracks);

        tracksRepository.getTopChartTracks(anyInt(), anyInt())
                .test()
                .assertValue(tracks);

        verify(tracksDataSource).getTopChartTracks(anyInt(), anyInt());
    }

    @Test
    public void whenSearchTrackByNameExpectLoadTracksFromDataSource() {
        String nameTrack = "Decadance";

        when(tracksDataSource.searchTrack(10, 1, nameTrack, ""))
                .thenReturn(Single.just(searchTrackModels));
        when(searchTrackListConverter.convert(searchTrackModels)).thenReturn(tracks);

        tracksRepository.searchTrack(10, 1, nameTrack, "")
                .test()
                .assertValue(tracks);

        verify(tracksDataSource).searchTrack(10, 1, nameTrack, "");
    }
}