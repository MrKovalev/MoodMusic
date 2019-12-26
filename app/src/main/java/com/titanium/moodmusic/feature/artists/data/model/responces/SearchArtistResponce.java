package com.titanium.moodmusic.feature.artists.data.model.responces;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.feature.artists.data.model.SearchArtistList;

public class SearchArtistResponce {

    @SerializedName("results")
    private SearchArtistList searchArtistList;

    public SearchArtistList getSearchArtist() {
        return searchArtistList;
    }
}
