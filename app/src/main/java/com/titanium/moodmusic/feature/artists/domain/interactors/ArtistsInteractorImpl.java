package com.titanium.moodmusic.feature.artists.domain.interactors;

import com.titanium.moodmusic.feature.artists.domain.entities.Artist;
import com.titanium.moodmusic.feature.artists.domain.repositories.ArtistsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ArtistsInteractorImpl implements ArtistsInteractor {

    private final ArtistsRepository artistsRepository;

    @Inject
    public ArtistsInteractorImpl(ArtistsRepository artistsRepository) {
        this.artistsRepository = artistsRepository;
    }

    @Override
    public Single<List<Artist>> getTopChartArtists(int page, int limit) {
        return artistsRepository.getTopChartArtists(page, limit);
    }

    @Override
    public Single<List<Artist>> searchArtist(int page, int limit, String name) {
        return artistsRepository.searchArtist(page, limit, name)
                .flattenAsObservable(artists -> artists)
                .distinct()
                .toList();
    }
}
