package com.titanium.moodmusic.artistsTests.datasources;

import com.titanium.moodmusic.component.database.dao.AlbumsDao;
import com.titanium.moodmusic.component.database.entity.AlbumEntity;
import com.titanium.moodmusic.shared.albums.data.datasources.AlbumsDataSource;
import com.titanium.moodmusic.shared.albums.data.datasources.AlbumsDataSourceImpl;
import com.titanium.moodmusic.shared.tracks.Track;

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
public class AlbumsDataSourceImplTest {

    private final AlbumsDao dao = mock(AlbumsDao.class);

    private final AlbumsDataSource albumsDataSource = new AlbumsDataSourceImpl(dao);

    private final AlbumEntity album = mock(AlbumEntity.class);
    private final List<AlbumEntity> albums = Collections.singletonList(album);

    @Test
    public void whenGetAlbumsExpectLoadAlbumsFromDb() {
        when(dao.getAllAlbums()).thenReturn(Single.just(albums));

        albumsDataSource.getAlbums()
                .test()
                .assertValue(albums);

        verify(dao).getAllAlbums();
    }

    @Test
    public void whenSaveAlbumsExpectInsertAlbumsToDb() {
        when(dao.insertAllAlbums(albums)).thenReturn(Completable.complete());

        albumsDataSource.saveAlbums(albums);

        verify(dao).insertAllAlbums(albums);
    }

    @Test
    public void whenSaveAlbumExpectInsertAlbumToDb() {
        when(dao.insertAlbum(album)).thenReturn(Completable.complete());

        albumsDataSource.saveAlbum(album);

        verify(dao).insertAlbum(album);
    }

    @Test
    public void whenClearAlbumsExpectClearAlbumsInDb() {
        when(dao.deleteAllAlbums()).thenReturn(Completable.complete());

        albumsDataSource.clearAlbums();

        verify(dao).deleteAllAlbums();
    }

    @Test
    public void whenUpdateAlbumWithNewTrackExpectUpdateAlbumInDb() {
        int id = 123;
        String name = "name";
        String description = "description";
        Track track = mock(Track.class);
        List<Track> tracks = Collections.singletonList(track);
        AlbumEntity album = new AlbumEntity(id, name, description, tracks);

        when(dao.updateAlbum(id, tracks)).thenReturn(Completable.complete());

        albumsDataSource.updateAlbumWithNewTracks(album);

        verify(dao).updateAlbum(id, tracks);
    }

    @Test
    public void whenDeleteAlbumExpectDeleteAlbumFromDb() {
        int id = 123;

        when(dao.deleteAlbumById(id)).thenReturn(Completable.complete());

        albumsDataSource.deleteAlbum(id);

        verify(dao).deleteAlbumById(id);
    }
}
