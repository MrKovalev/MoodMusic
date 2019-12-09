package com.titanium.moodmusic.ui.fragments.artists;

import com.titanium.moodmusic.data.model.responces.SearchArtistResponce;
import com.titanium.moodmusic.data.model.responces.TopChartArtistsResponce;

import io.reactivex.Single;

public interface IArtistsInteractor {
    Single<TopChartArtistsResponce> getTopChartArtists(int page, int limit, String apiKey);
    Single<SearchArtistResponce> searchArtist(int page, int limit, String name, String apiKey);
}
