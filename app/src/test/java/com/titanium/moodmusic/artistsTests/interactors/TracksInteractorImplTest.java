package com.titanium.moodmusic.artistsTests.interactors;

import com.titanium.moodmusic.feature.tracks.domain.interactors.TracksInteractor;
import com.titanium.moodmusic.feature.tracks.domain.interactors.TracksInteractorImpl;
import com.titanium.moodmusic.feature.tracks.domain.repositories.TracksRepository;
import com.titanium.moodmusic.shared.tracks.Track;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.Single;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TracksInteractorImplTest {

    private final TracksRepository tracksRepository = mock(TracksRepository.class);

    private final TracksInteractor tracksInteractor = new TracksInteractorImpl(tracksRepository);

    private final Track track = mock(Track.class);
    private final ArrayList<Track> tracks = new ArrayList<>(Collections.singletonList(track));

    private final int limit = 10;
    private final int page = 1;

    @Test
    public void whenGetTracksExpectLoadTracksFromRepository() {
        when(tracksRepository.getTopChartTracks(page, limit)).thenReturn(Single.just(tracks));

        tracksInteractor.getTopChartTracks(page, limit)
                .test().assertValue(tracks);

        verify(tracksRepository).getTopChartTracks(page, limit);
    }

    @Test
    public void whenSearchTrackExpectGetSearchedTracksFromRepository() {
        String query = "query";

        when(tracksRepository.searchTrack(limit, page, query, null))
                .thenReturn(Single.just(tracks));

        tracksInteractor.searchTrack(limit, page, query, null)
                .test()
                .assertValue(tracks);

        verify(tracksRepository).searchTrack(limit, page, query, null);
    }
}
