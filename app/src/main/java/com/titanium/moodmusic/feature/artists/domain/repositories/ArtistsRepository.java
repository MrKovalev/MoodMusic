package com.titanium.moodmusic.feature.artists.domain.repositories;

import com.titanium.moodmusic.feature.artists.domain.entities.Artist;

import java.util.List;

import io.reactivex.Single;

public interface ArtistsRepository {

    Single<List<Artist>> getTopChartArtists(int page, int limit);

    Single<List<Artist>> searchArtist(int page, int limit, String name);
}
