package com.titanium.moodmusic.feature.artists.data.datasources.stub;

import com.titanium.moodmusic.feature.artists.data.datasources.ArtistsDataSource;
import com.titanium.moodmusic.feature.artists.data.model.ArtistModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ArtistsStubDataSourceImpl implements ArtistsDataSource {

    @Inject
    public ArtistsStubDataSourceImpl() {
    }

    @Override
    public Single<List<ArtistModel>> getTopChartArtists(int page, int limit) {
        final ArrayList<ArtistModel> items = new ArrayList<>();
        items.add(new ArtistModel("123", "Disturbed", new ArrayList<>(), "23", "100", "http"));
        items.add(new ArtistModel("124", "Kiss", new ArrayList<>(), "13", "55", "http1"));
        items.add(new ArtistModel("125", "Scorpions", new ArrayList<>(), "3", "12", "http2"));
        items.add(new ArtistModel("126", "Rammstain", new ArrayList<>(), "55", "555", "http3"));

        return Single.just(items);
    }

    @Override
    public Single<List<ArtistModel>> searchArtist(int page, int limit, String name) {
        final ArrayList<ArtistModel> items = new ArrayList<>();
        items.add(new ArtistModel("123", "Disturbed_search", new ArrayList<>(), "23", "100", "http"));
        items.add(new ArtistModel("124", "Kiss_search", new ArrayList<>(), "13", "55", "http1"));
        items.add(new ArtistModel("125", "Scorpions_search", new ArrayList<>(), "3", "12", "http2"));
        items.add(new ArtistModel("126", "Rammstain_search", new ArrayList<>(), "55", "555", "http3"));

        return Single.just(items);
    }
}
