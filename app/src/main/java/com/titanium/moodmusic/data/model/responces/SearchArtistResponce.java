package com.titanium.moodmusic.data.model.responces;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.data.model.artists.SearchArtistList;

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
