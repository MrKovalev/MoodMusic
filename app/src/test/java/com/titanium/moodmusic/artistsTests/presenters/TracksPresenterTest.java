package com.titanium.moodmusic.artistsTests.presenters;

import com.titanium.moodmusic.artistsTests.rules.TestSchedulerRule;
import com.titanium.moodmusic.feature.tracks.domain.interactors.TracksInteractor;
import com.titanium.moodmusic.feature.tracks.presentation.TracksPresenter;
import com.titanium.moodmusic.feature.tracks.presentation.TracksView;
import com.titanium.moodmusic.feature.tracks.presentation.TracksView$$State;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;
import com.titanium.moodmusic.shared.albums.domain.interactors.AlbumsInteractor;
import com.titanium.moodmusic.shared.error.handler.ErrorHandler;
import com.titanium.moodmusic.shared.search.domain.usecases.SearchQueryInteractor;
import com.titanium.moodmusic.shared.tracks.Track;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TracksPresenterTest {

    @Rule
    public TestRule rule = new TestSchedulerRule();

    private TracksInteractor tracksInteractor = mock(TracksInteractor.class);
    private AlbumsInteractor albumsInteractor = mock(AlbumsInteractor.class);
    private final SearchQueryInteractor<String> searchQueryInteractor = mock(SearchQueryInteractor.class);
    private final ErrorHandler errorHandler = mock(ErrorHandler.class);
    private TracksView$$State viewState = mock(TracksView$$State.class);
    private TracksView view = mock(TracksView.class);

    private final TracksPresenter presenter = new TracksPresenter(tracksInteractor, albumsInteractor, searchQueryInteractor, errorHandler);

    private final Track track = mock(Track.class);
    private final List<Track> tracks = new ArrayList<>();
    private final List<Album> albums = new ArrayList<>();

    @Before
    public void setUp() {
        when(searchQueryInteractor.getObservable()).thenReturn(Observable.never());
        when(tracksInteractor.getTopChartTracks(anyInt(), anyInt())).thenReturn(Single.just(tracks));

        presenter.setViewState(viewState);
    }

    @Test
    public void whenOpenScreenExpectTracksSuccessLoaded() {
        presenter.attachView(view);

        verify(viewState).attachView(view);
        verify(viewState).showProgress();
        verify(viewState).hideProgress();
        verify(viewState).showLoadedTracks(tracks);
    }

    @Test
    public void whenSearchTrackExpectSuccessSearched() {
        String query = "lala";

        when(searchQueryInteractor.getObservable()).thenReturn(Observable.just(query));
        when(tracksInteractor.searchTrack(anyInt(), anyInt(), eq(query), any()))
                .thenReturn(Single.just(tracks));

        presenter.attachView(view);

        verify(viewState).showSearchedTracks(tracks);
    }

    @Test
    public void whenOpenTrackDetailsExpectDetailsScreenShown() {
        presenter.onOpenTrackDetailClicked(track);

        verify(viewState).openTrackDetail(track);
    }

    @Test
    public void whenClickOnTrackMenuExpectShowDialog() {
        when(albumsInteractor.getFavoriteAlbums()).thenReturn(Single.just(albums));

        presenter.attachView(view);
        clearInvocations(viewState);

        presenter.onAddTrackClicked(track);

        verify(viewState).showProgress();
        verify(viewState).hideProgress();
        verify(viewState).showAddTrackDialog(track, albums);
    }

    @Test
    public void whenSaveTrackInAlbumExpectSuccessSaved() {
        Album album = mock(Album.class);

        when(albumsInteractor.updateAlbumWithNewTracks(album)).thenReturn(Completable.complete());

        presenter.attachView(view);
        clearInvocations(viewState, searchQueryInteractor, albumsInteractor);

        presenter.onSaveTrack(album, track);

        verify(viewState).showProgress();
        verify(viewState).hideProgress();
        verify(viewState).showSuccessSavedTrack();
    }
}
