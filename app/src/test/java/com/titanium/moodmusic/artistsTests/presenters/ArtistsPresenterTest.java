package com.titanium.moodmusic.artistsTests.presenters;

import com.titanium.moodmusic.artistsTests.rules.TestSchedulerRule;
import com.titanium.moodmusic.feature.artists.domain.entities.Artist;
import com.titanium.moodmusic.feature.artists.domain.interactors.ArtistsInteractor;
import com.titanium.moodmusic.feature.artists.presentation.ArtistsPresenter;
import com.titanium.moodmusic.feature.artists.presentation.ArtistsView;
import com.titanium.moodmusic.feature.artists.presentation.ArtistsView$$State;
import com.titanium.moodmusic.shared.error.Message;
import com.titanium.moodmusic.shared.error.handler.ErrorHandler;
import com.titanium.moodmusic.shared.search.domain.usecases.SearchQueryInteractor;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArtistsPresenterTest {

    @Rule
    public final TestRule rule = new TestSchedulerRule();

    private final ArtistsInteractor artistsInteractor = mock(ArtistsInteractor.class);
    private final SearchQueryInteractor<String> searchQueryInteractor = mock(SearchQueryInteractor.class);
    private final ErrorHandler errorHandler = mock(ErrorHandler.class);
    private final ArtistsView view = mock(ArtistsView.class);
    private final ArtistsView$$State viewState = mock(ArtistsView$$State.class);

    private ArtistsPresenter presenter;

    private final Artist artist = mock(Artist.class);
    private final List<Artist> artists = Collections.singletonList(artist);

    @Before
    public void setUp() {
        when(searchQueryInteractor.getObservable()).thenReturn(Observable.never());
        when(artistsInteractor.getTopChartArtists(anyInt(), anyInt())).thenReturn(Single.just(artists));

        presenter = new ArtistsPresenter(artistsInteractor, searchQueryInteractor, errorHandler);
        presenter.setViewState(viewState);
    }

    @Test
    public void whenOpenScreenExpectArtistsSuccessLoaded() {
        presenter.attachView(view);

        verify(viewState).attachView(view);
        verify(viewState).showProgress();
        verify(viewState).hideProgress();
        verify(viewState).showChartArtists(artists);
    }

    @Test
    public void whenLoadArtistsWithErrorExpectShowErrorDialog() {
        String errorText = "error";
        RuntimeException exception = new RuntimeException(errorText);
        Message message = new Message(errorText);

        when(artistsInteractor.getTopChartArtists(anyInt(), anyInt())).thenReturn(Single.error(exception));
        when(errorHandler.processError(exception)).thenReturn(message);

        presenter.attachView(view);
        clearInvocations(viewState, artistsInteractor, errorHandler);

        presenter.onRefreshArtists();

        verify(errorHandler).processError(exception);
        verify(viewState).showError(message);
    }

    @Test
    public void whenSearchArtistExpectSuccessSearched() {
        String artistName = "Distrurbed";

        when(searchQueryInteractor.getObservable()).thenReturn(Observable.just(artistName));
        when(artistsInteractor.searchArtist(anyInt(), anyInt(), eq(artistName)))
                .thenReturn(Single.just(artists));

        presenter.attachView(view);

        verify(viewState).clearArtists();
        verify(viewState, times(2)).showProgress();
        verify(searchQueryInteractor).getObservable();
        verify(artistsInteractor).searchArtist(anyInt(), anyInt(), eq(artistName));
        verify(viewState, times(2)).hideProgress();
        verify(viewState).showSearchedArtists(artists);
    }
}
