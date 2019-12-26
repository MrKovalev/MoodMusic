package com.titanium.moodmusic.artistsTests.repositories;

import com.titanium.moodmusic.component.database.entity.AlbumEntity;
import com.titanium.moodmusic.shared.albums.data.converters.AlbumDbConverter;
import com.titanium.moodmusic.shared.albums.data.converters.AlbumDbListConverter;
import com.titanium.moodmusic.shared.albums.data.converters.AlbumEntityListConverter;
import com.titanium.moodmusic.shared.albums.data.datasources.AlbumsDataSource;
import com.titanium.moodmusic.shared.albums.data.repositories.AlbumsRepositoryImpl;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;
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
public class AlbumsRepositoryImplTest {

    private final AlbumsDataSource albumsDataSource = mock(AlbumsDataSource.class);
    private final AlbumDbConverter albumDbConverter = mock(AlbumDbConverter.class);
    private final AlbumEntityListConverter albumEntityListConverter = mock(AlbumEntityListConverter.class);
    private final AlbumDbListConverter albumDbListConverter = mock(AlbumDbListConverter.class);

    private final AlbumsRepository albumsRepository = new AlbumsRepositoryImpl(
            albumsDataSource,
            albumDbConverter,
            albumEntityListConverter,
            albumDbListConverter
    );

    private final Album album = mock(Album.class);
    private final AlbumEntity albumEntity = mock(AlbumEntity.class);

    private final List<Album> albums = Collections.singletonList(album);
    private final List<AlbumEntity> albumEntities = Collections.singletonList(albumEntity);

    @Test
    public void whenGetAlbumsExpectLoadAndConvertAlbumsFromDataSource() {
        when(albumsDataSource.getAlbums()).thenReturn(Single.just(albumEntities));
        when(albumEntityListConverter.convert(albumEntities)).thenReturn(albums);

        albumsRepository.getFavoriteAlbums()
                .test()
                .assertValue(albums);

        verify(albumsDataSource).getAlbums();
        verify(albumEntityListConverter).convert(albumEntities);
    }

    @Test
    public void whenSaveAlbumsExpectSuccessSavedToDataSource() {
        when(albumsDataSource.saveAlbums(albumEntities))
                .thenReturn(Completable.complete());
        when(albumDbListConverter.convert(albums)).thenReturn(albumEntities);

        albumsRepository.saveAlbums(albums);

        verify(albumDbListConverter).convert(albums);
        verify(albumsDataSource).saveAlbums(albumEntities);
    }

    @Test
    public void whenSaveAlbumExpectSuccessSavedToDataSource() {
        when(albumsDataSource.saveAlbum(albumEntity))
                .thenReturn(Completable.complete());
        when(albumDbConverter.convert(album)).thenReturn(albumEntity);

        albumsRepository.saveAlbum(album);

        verify(albumDbConverter).convert(album);
        verify(albumsDataSource).saveAlbum(albumEntity);
    }

    @Test
    public void whenClearAlbumsExpectClearAllAlbumsInDataSource() {
        when(albumsDataSource.clearAlbums()).thenReturn(Completable.complete());

        albumsRepository.clearAlbums();

        verify(albumsDataSource).clearAlbums();
    }

    @Test
    public void whenUpdateAlbumExpectUpdateAlbumInDataSource() {
        when(albumsDataSource.updateAlbumWithNewTracks(albumEntity))
                .thenReturn(Completable.complete());
        when(albumDbConverter.convert(album)).thenReturn(albumEntity);

        albumsRepository.updateAlbumWithNewTracks(album);

        verify(albumsDataSource).updateAlbumWithNewTracks(albumEntity);
        verify(albumDbConverter).convert(album);
    }

    @Test
    public void whenDeleteAlbumExpectDeleteAlbumFromDataSource() {
        int albumId = 123;

        when(albumsDataSource.deleteAlbum(albumId))
                .thenReturn(Completable.complete());

        albumsRepository.deleteAlbum(albumId);

        verify(albumsDataSource).deleteAlbum(albumId);
    }
}
