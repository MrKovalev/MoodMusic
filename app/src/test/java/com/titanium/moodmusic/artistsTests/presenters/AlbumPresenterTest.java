package com.titanium.moodmusic.artistsTests.presenters;

import com.titanium.moodmusic.artistsTests.rules.TestSchedulerRule;
import com.titanium.moodmusic.feature.albums.presentation.AlbumsPresenter;
import com.titanium.moodmusic.feature.albums.presentation.AlbumsView;
import com.titanium.moodmusic.feature.albums.presentation.AlbumsView$$State;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;
import com.titanium.moodmusic.shared.albums.domain.interactors.AlbumsInteractor;
import com.titanium.moodmusic.shared.error.Message;
import com.titanium.moodmusic.shared.error.handler.ErrorHandler;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AlbumPresenterTest {

    @Rule
    public TestRule rule = new TestSchedulerRule();

    private final AlbumsInteractor albumsInteractor = mock(AlbumsInteractor.class);
    private final ErrorHandler errorHandler = mock(ErrorHandler.class);

    private AlbumsView view = mock(AlbumsView.class);
    private AlbumsView$$State viewState = mock(AlbumsView$$State.class);
    private final AlbumsPresenter albumsPresenter = new AlbumsPresenter(albumsInteractor, errorHandler);

    private final Album album = mock(Album.class);
    private final List<Album> albums = Collections.singletonList(album);

    @Before
    public void setUp() {
        when(albumsInteractor.getFavoriteAlbums()).thenReturn(Single.just(albums));

        albumsPresenter.setViewState(viewState);
        albumsPresenter.attachView(view);
    }

    @Test
    public void whenOpenScreenExpectAlbumsSuccessLoaded() {
        verify(viewState).showProgress();
        verify(albumsInteractor).getFavoriteAlbums();
        verify(viewState).hideProgress();
    }

    @Test
    public void whenAddNewAlbumClickedExpectShowCreationDialog() {
        albumsPresenter.onAddNewAlbumClicked();

        verify(viewState).showAddNewAlbumDialog();
    }

    @Test
    public void whenClickOnMenuAlbumExpectShowChooseActionDialog() {
        int position = 1;

        albumsPresenter.onSelectMenuAlbumClicked(album, position);

        verify(viewState).showChooseActionDialog(album, position);
    }

    @Test
    public void whenRenameAlbumSelectedExpectRenameDialog() {
        albumsPresenter.onEditAlbumSelected(album);

        verify(viewState).showRenameAlbumDialog(album);
    }

    @Test
    public void whenDeleteAlbumSelectedExpectDeleteAlbum() {
        int position = 1;

        when(albumsInteractor.deleteAlbum(album.getAlbumId())).thenReturn(Completable.complete());

        albumsPresenter.onDeleteAlbumSelected(position, album);

        verify(albumsInteractor).deleteAlbum(album.getAlbumId());
    }

    @Test
    public void whenOpenAlbumDetailsExpectAlbumDetailsScreenShown() {
        albumsPresenter.onOpenAlbumClicked(album);

        verify(viewState).openAlbumDetails(album);
    }

    @Test
    public void whenAlbumsLoadsWithErrorExpectShowErrorDialog() {
        String errorText = "error";
        RuntimeException exception = new RuntimeException(errorText);
        Message message = new Message(errorText);

        when(errorHandler.processError(exception)).thenReturn(message);
        when(albumsInteractor.getFavoriteAlbums()).thenReturn(Single.error(exception));

        albumsPresenter.getFavoriteAlbums();

        verify(errorHandler).processError(exception);
        verify(viewState).showError(message);
    }
}
