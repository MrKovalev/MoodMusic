package com.titanium.moodmusic.artistsTests.interactors;

import com.titanium.moodmusic.feature.artists.domain.entities.Artist;
import com.titanium.moodmusic.feature.artists.domain.interactors.ArtistsInteractor;
import com.titanium.moodmusic.feature.artists.domain.interactors.ArtistsInteractorImpl;
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
public class ArtistsInteractorImplTest {

    private final ArtistsRepository artistsRepository = mock(ArtistsRepository.class);

    private final ArtistsInteractor artistsInteractor = new ArtistsInteractorImpl(artistsRepository);

    private final Artist artist = mock(Artist.class);
    private final List<Artist> artists = Collections.singletonList(artist);

    @Test
    public void whenGetChartArtistsExpectLoadArtistsFromRepository() {
        when(artistsRepository.getTopChartArtists(anyInt(), anyInt()))
                .thenReturn(Single.just(artists));

        artistsInteractor.getTopChartArtists(anyInt(), anyInt())
                .test()
                .assertValue(artists);

        verify(artistsRepository).getTopChartArtists(anyInt(), anyInt());
    }

    @Test
    public void whenSearchArtistsExpectLoadSearchedArtistsFromRepository() {
        when(artistsRepository.searchArtist(anyInt(), anyInt(), anyString()))
                .thenReturn(Single.just(artists));

        artistsInteractor.searchArtist(anyInt(), anyInt(), anyString());

        verify(artistsRepository).searchArtist(anyInt(), anyInt(), anyString());
    }
}
