package com.titanium.moodmusic.feature.artists.data.repositories;

import com.titanium.moodmusic.feature.artists.data.converters.ArtistListConverter;
import com.titanium.moodmusic.feature.artists.data.datasources.ArtistsDataSource;
import com.titanium.moodmusic.feature.artists.domain.entities.Artist;
import com.titanium.moodmusic.feature.artists.domain.repositories.ArtistsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ArtistsRepositoryImpl implements ArtistsRepository {

    private final ArtistsDataSource artistsDataSource;
    private final ArtistListConverter artistListConverter;

    @Inject
    public ArtistsRepositoryImpl(ArtistsDataSource artistsDataSource, ArtistListConverter artistListConverter) {
        this.artistsDataSource = artistsDataSource;
        this.artistListConverter = artistListConverter;
    }

    @Override
    public Single<List<Artist>> getTopChartArtists(int page, int limit) {
        return artistsDataSource.getTopChartArtists(page, limit).map(artistListConverter::convert);
    }

    @Override
    public Single<List<Artist>> searchArtist(int page, int limit, String name) {
        return artistsDataSource.searchArtist(page, limit, name).map(artistListConverter::convert);
    }
}