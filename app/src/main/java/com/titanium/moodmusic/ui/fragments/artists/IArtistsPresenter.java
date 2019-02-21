package com.titanium.moodmusic.ui.fragments.artists;

public interface IArtistsPresenter {
    void onDestroy();

    void getTopChartArtists(int limit, String apiKey);
}
