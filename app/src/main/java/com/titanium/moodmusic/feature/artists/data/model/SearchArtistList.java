package com.titanium.moodmusic.feature.artists.data.model;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.feature.artists.data.model.ArtistList;

public class SearchArtistList {

    @SerializedName("opensearch:totalResults")
    private int totalResults;
    @SerializedName("artistmatches")
    private ArtistList artistListMatches;

    public ArtistList getArtistListMatches() {
        return artistListMatches;
    }

    public void setArtistListMatches(ArtistList artistListMatches) {
        this.artistListMatches = artistListMatches;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
