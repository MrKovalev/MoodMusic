package com.titanium.moodmusic.artistsTests.datasources;

import com.titanium.moodmusic.feature.tracks.data.api.TracksApi;
import com.titanium.moodmusic.feature.tracks.data.datasources.TracksDataSource;
import com.titanium.moodmusic.feature.tracks.data.datasources.TracksDataSourceImpl;
import com.titanium.moodmusic.feature.tracks.data.model.responces.SearchTrackByNameResponce;
import com.titanium.moodmusic.feature.tracks.data.model.responces.TopChartTracksResponce;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TracksDataSourceImplTest {

    private final TracksApi api = mock(TracksApi.class);

    private final TracksDataSource tracksDataSource = new TracksDataSourceImpl(api);

    private final int limit = 10;
    private final int page = 1;

    @Test
    public void whenGetTopChartTracksApiMethodCalled() {
        final TopChartTracksResponce topChartTracksResponce = mock(TopChartTracksResponce.class);

        when(api.getTopChartTracks(page, limit)).thenReturn(Single.just(topChartTracksResponce));

        tracksDataSource.getTopChartTracks(page, limit);

        verify(api).getTopChartTracks(page, limit);
    }

    @Test
    public void whenSearchTopChartTracksByNameApiMethodCalled() {
        final String nameTrack = "decadance";
        final SearchTrackByNameResponce searchTrackByNameResponce = mock(SearchTrackByNameResponce.class);

        when(api.searchTrack(page, limit, nameTrack, null)).thenReturn(Single.just(searchTrackByNameResponce));

        tracksDataSource.searchTrack(page, limit, nameTrack, null);

        verify(api).searchTrack(page, limit, nameTrack, null);
    }
}
