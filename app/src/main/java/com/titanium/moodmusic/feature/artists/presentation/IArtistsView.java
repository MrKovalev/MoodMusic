package com.titanium.moodmusic.feature.artists.presentation;

import com.titanium.moodmusic.feature.artists.data.model.Artist;

import java.util.List;

public interface IArtistsView {
    void showProgress();
    void hideProgress();

    void loadArtists(List<Artist> artistList);
    void searchArtists(List<Artist> artistList);

    void showError();
}
