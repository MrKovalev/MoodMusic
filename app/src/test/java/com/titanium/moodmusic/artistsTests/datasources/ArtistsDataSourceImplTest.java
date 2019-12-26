package com.titanium.moodmusic.artistsTests.datasources;

import com.titanium.moodmusic.feature.artists.data.api.ArtistsApi;
import com.titanium.moodmusic.feature.artists.data.datasources.ArtistsDataSource;
import com.titanium.moodmusic.feature.artists.data.datasources.ArtistsDataSourceImpl;
import com.titanium.moodmusic.feature.artists.data.model.responces.SearchArtistResponce;
import com.titanium.moodmusic.feature.artists.data.model.responces.TopChartArtistsResponce;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArtistsDataSourceImplTest {

    private final ArtistsApi api = mock(ArtistsApi.class);

    private final ArtistsDataSource artistsDataSource = new ArtistsDataSourceImpl(api);

    private final int limit = 10;
    private final int page = 1;

    @Test
    public void whenGetTopChartArtistsApiMethodCalled() {
        final TopChartArtistsResponce topChartArtistsResponce = mock(TopChartArtistsResponce.class);

        when(api.getTopChartArtists(page, limit)).thenReturn(Single.just(topChartArtistsResponce));

        artistsDataSource.getTopChartArtists(page, limit);

        verify(api).getTopChartArtists(page, limit);
    }

    @Test
    public void whenSearchTopChartArtistsApiMethodCalled() {
        final String nameArtist = "disturbed";
        final SearchArtistResponce searchArtistResponce = mock(SearchArtistResponce.class);

        when(api.searchArtist(page, limit, nameArtist)).thenReturn(Single.just(searchArtistResponce));

        artistsDataSource.searchArtist(page, limit, nameArtist);

        verify(api).searchArtist(page, limit, nameArtist);
    }
}
