package com.titanium.moodmusic.feature.artists.data.datasources;

import com.titanium.moodmusic.feature.artists.data.api.ArtistsApi;
import com.titanium.moodmusic.feature.artists.data.model.ArtistModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ArtistsDataSourceImpl implements ArtistsDataSource {

    private final ArtistsApi artistsApi;

    @Inject
    public ArtistsDataSourceImpl(ArtistsApi artistsApi) {
        this.artistsApi = artistsApi;
    }

    @Override
    public Single<List<ArtistModel>> getTopChartArtists(int page, int limit) {
        return artistsApi.getTopChartArtists(page, limit).map(topChartArtistsResponce ->
                topChartArtistsResponce.getArtistModelListResponce().getArtistModelList()
        );
    }

    @Override
    public Single<List<ArtistModel>> searchArtist(int page, int limit, String name) {
        return artistsApi.searchArtist(page, limit, name).map(
                searchArtistResponce -> {
                    if (searchArtistResponce.getSearchArtist() != null) {
                        return searchArtistResponce.getSearchArtist().getArtistModelListMatches().getArtistModelList();
                    } else {
                        return new ArrayList<>();
                    }
                }
        );
    }
}
