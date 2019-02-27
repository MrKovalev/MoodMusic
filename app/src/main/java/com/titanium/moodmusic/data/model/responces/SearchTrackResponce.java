package com.titanium.moodmusic.data.model.responces;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.data.model.tracks.SearchTrackList;

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
