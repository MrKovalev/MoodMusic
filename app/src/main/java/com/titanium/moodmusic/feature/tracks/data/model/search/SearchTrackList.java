package com.titanium.moodmusic.feature.tracks.data.model.search;

import com.google.gson.annotations.SerializedName;

public class SearchTrackList {

    @SerializedName("trackmatches")
    private SearchTrackModelList searchTrackModelList;

    public SearchTrackModelList getTrackListMatches() {
        return searchTrackModelList;
    }
}
