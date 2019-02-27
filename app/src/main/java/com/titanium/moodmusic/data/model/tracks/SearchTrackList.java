package com.titanium.moodmusic.data.model.tracks;

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
