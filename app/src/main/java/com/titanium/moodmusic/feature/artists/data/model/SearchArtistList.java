package com.titanium.moodmusic.feature.artists.data.model;

import com.google.gson.annotations.SerializedName;

public class SearchArtistList {

    @SerializedName("opensearch:totalResults")
    private int totalResults;

    @SerializedName("artistmatches")
    private ArtistModelList artistModelListMatches;

    public ArtistModelList getArtistModelListMatches() {
        return artistModelListMatches;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
