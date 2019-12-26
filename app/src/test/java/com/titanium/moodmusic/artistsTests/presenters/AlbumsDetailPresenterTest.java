package com.titanium.moodmusic.artistsTests.presenters;

import com.titanium.moodmusic.feature.album.detail.presentation.AlbumDetailsPresenter;
import com.titanium.moodmusic.feature.album.detail.presentation.AlbumDetailsView;
import com.titanium.moodmusic.feature.album.detail.presentation.AlbumDetailsView$$State;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AlbumsDetailPresenterTest {

    private final Album album = mock(Album.class);

    private final AlbumDetailsView view = mock(AlbumDetailsView.class);
    private final AlbumDetailsView$$State viewState = mock(AlbumDetailsView$$State.class);
    private final AlbumDetailsPresenter albumDetailsPresenter = new AlbumDetailsPresenter(album);

    @Test
    public void whenOpenScreenExpectTrackListLoaded() {
        albumDetailsPresenter.setViewState(viewState);
        albumDetailsPresenter.attachView(view);

        verify(viewState).showTracks(album.getTracks());
    }
}
