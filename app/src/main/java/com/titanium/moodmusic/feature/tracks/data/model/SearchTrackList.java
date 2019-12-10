package com.titanium.moodmusic.feature.tracks.data.model;

import com.google.gson.annotations.SerializedName;

public class SearchTrackList {

    @SerializedName("trackmatches")
    private TrackList trackList;

    public TrackList getTrackListMatches() {
        return trackList;
    }
    public void setTrackListMatches(TrackList trackList) {
        this.trackList = trackList;
    }
}
