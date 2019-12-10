package com.titanium.moodmusic.feature.artists.domain;

import com.titanium.moodmusic.feature.artists.data.model.SearchArtistResponce;
import com.titanium.moodmusic.feature.artists.data.model.TopChartArtistsResponce;

import io.reactivex.Single;

public interface IArtistsInteractor {
    Single<TopChartArtistsResponce> getTopChartArtists(int page, int limit, String apiKey);
    Single<SearchArtistResponce> searchArtist(int page, int limit, String name, String apiKey);
}
