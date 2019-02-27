package com.titanium.moodmusic.ui.fragments.artists;

import com.titanium.moodmusic.data.model.artists.Artist;

import java.util.List;

public interface IArtistsView {
    void showProgress();
    void hideProgress();

    void loadArtists(List<Artist> artistList);
    void searchArtists(List<Artist> artistList);

    void showError();
    void showEmpty();
    void hideEmpty();
}
