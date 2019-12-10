package com.titanium.moodmusic.feature.artists.data.model;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.feature.artists.data.model.SearchArtistList;

public class SearchArtistResponce {

    @SerializedName("results")
    private SearchArtistList searchArtistList;

    public SearchArtistList getSearchArtist() {
        return searchArtistList;
    }

    public void setSearchArtist(SearchArtistList searchArtistList) {
        this.searchArtistList = searchArtistList;
    }
}
