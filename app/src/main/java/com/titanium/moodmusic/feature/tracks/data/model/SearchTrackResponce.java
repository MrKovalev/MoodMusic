package com.titanium.moodmusic.feature.tracks.data.model;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.feature.tracks.data.model.SearchTrackList;

public class SearchTrackResponce {

    @SerializedName("results")
    SearchTrackList trackListResponce;

    public SearchTrackList getTrackListResponce() {
        return trackListResponce;
    }

    public void setTrackListResponce(SearchTrackList trackListResponce) {
        this.trackListResponce = trackListResponce;
    }
}
