package com.titanium.moodmusic.feature.artists.presentation;

public interface IArtistsPresenter {
    void onDestroy();

    void getTopChartArtists(int page,int limit, String apiKey);
    void searchArtist(int page,int limit, String name, String apiKey);
}
