package com.titanium.moodmusic.feature.tracks.data.model.responces;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.feature.tracks.data.model.search.SearchTrackList;

public class SearchTrackByNameResponce {

    @SerializedName("results")
    private SearchTrackList trackListResponce;

    public SearchTrackList getTrackListResponce() {
        return trackListResponce;
    }
}
