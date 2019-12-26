package com.titanium.moodmusic.artistsTests.repositories;

import com.titanium.moodmusic.feature.artists.data.converters.ArtistListConverter;
import com.titanium.moodmusic.feature.artists.data.datasources.ArtistsDataSource;
import com.titanium.moodmusic.feature.artists.data.model.ArtistModel;
import com.titanium.moodmusic.feature.artists.data.repositories.ArtistsRepositoryImpl;
import com.titanium.moodmusic.feature.artists.domain.entities.Artist;
import com.titanium.moodmusic.feature.artists.domain.repositories.ArtistsRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArtistsRepositoryImplTest {

    private final ArtistsDataSource artistsDataSource = mock(ArtistsDataSource.class);
    private final ArtistListConverter artistListConverter = mock(ArtistListConverter.class);

    private final ArtistsRepository artistsRepository = new ArtistsRepositoryImpl(
            artistsDataSource,
            artistListConverter
    );

    private final ArtistModel artistModel = mock(ArtistModel.class);
    private final Artist artist = mock(Artist.class);

    private final List<ArtistModel> artistModelList = Collections.singletonList(artistModel);
    private final List<Artist> artists = Collections.singletonList(artist);

    @Test
    public void whenGetTopArtistsExpectLoadAndConvertFromDataSource() {
        when(artistsDataSource.getTopChartArtists(anyInt(), anyInt()))
                .thenReturn(Single.just(artistModelList));

        when(artistListConverter.convert(artistModelList)).thenReturn(artists);

        artistsRepository.getTopChartArtists(anyInt(), anyInt())
                .test().assertValue(artists);

        verify(artistsDataSource).getTopChartArtists(anyInt(), anyInt());
        verify(artistListConverter).convert(artistModelList);
    }

    @Test
    public void whenSearchArtistsExpectSearchAndConvertFromDataSource() {
        when(artistsDataSource.searchArtist(anyInt(), anyInt(), anyString()))
                .thenReturn(Single.just(artistModelList));

        when(artistListConverter.convert(artistModelList)).thenReturn(artists);

        artistsRepository.searchArtist(anyInt(), anyInt(), anyString())
                .test().assertValue(artists);

        verify(artistsDataSource).searchArtist(anyInt(), anyInt(), anyString());
        verify(artistListConverter).convert(artistModelList);
    }
}
