package com.titanium.moodmusic.artistsTests.interactors;

import com.titanium.moodmusic.shared.albums.domain.entiries.Album;
import com.titanium.moodmusic.shared.albums.domain.interactors.AlbumsInteractor;
import com.titanium.moodmusic.shared.albums.domain.interactors.AlbumsInteractorImpl;
import com.titanium.moodmusic.shared.albums.domain.repositories.AlbumsRepository;

import org.junit.Test;
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
public class AlbumsInteractorImplTest {

    private final AlbumsRepository albumsRepository = mock(AlbumsRepository.class);

    private final AlbumsInteractor albumsInteractor = new AlbumsInteractorImpl(albumsRepository);

    private final Album album = mock(Album.class);
    private final List<Album> albums = Collections.singletonList(album);

    @Test
    public void whenGetAlbumsExpectLoadAlbumsFromRepository() {
        when(albumsRepository.getFavoriteAlbums()).thenReturn(Single.just(albums));

        albumsInteractor.getFavoriteAlbums()
                .test().assertValue(albums);

        verify(albumsRepository).getFavoriteAlbums();
    }

    @Test
    public void whenSaveAlbumsExpectSaveAlbumsInRepository() {
        when(albumsRepository.saveAlbums(albums)).thenReturn(Completable.complete());

        albumsInteractor.saveAlbums(albums);

        verify(albumsRepository).saveAlbums(albums);
    }

    @Test
    public void whenSaveAlbumExpectSaveAlbumInRepository() {
        when(albumsRepository.saveAlbum(album)).thenReturn(Completable.complete());

        albumsInteractor.saveAlbum(album);

        verify(albumsRepository).saveAlbum(album);
    }

    @Test
    public void whenUpdateAlbumExpectUpdateAlbumInRepository() {
        when(albumsRepository.updateAlbumWithNewTracks(album)).thenReturn(Completable.complete());

        albumsInteractor.updateAlbumWithNewTracks(album);

        verify(albumsRepository).updateAlbumWithNewTracks(album);
    }

    @Test
    public void whenDeleteAlbumExpectDeleteAlbumFromRepository() {
        int id = 123;

        when(albumsRepository.deleteAlbum(id)).thenReturn(Completable.complete());

        albumsInteractor.deleteAlbum(id);

        verify(albumsRepository).deleteAlbum(id);
    }
}
