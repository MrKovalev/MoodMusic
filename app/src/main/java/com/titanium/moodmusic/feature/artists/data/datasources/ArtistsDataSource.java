package com.titanium.moodmusic.feature.artists.data.datasources;

import com.titanium.moodmusic.feature.artists.data.model.ArtistModel;

import java.util.List;

import io.reactivex.Single;

public interface ArtistsDataSource {

    Single<List<ArtistModel>> getTopChartArtists(int page, int limit);

    Single<List<ArtistModel>> searchArtist(int page, int limit, String name);
}
