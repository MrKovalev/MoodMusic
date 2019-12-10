package com.titanium.moodmusic.feature.tracks.data.model;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.feature.tracks.data.model.TrackList;

public class TracksByArtistResponce {
    @SerializedName("toptracks")
    private TrackList tracksByArtistResponce;

    public TrackList getTracksByArtistResponce() {
        return tracksByArtistResponce;
    }

    public void setTracksByArtistResponce(TrackList tracksByArtistResponce) {
        this.tracksByArtistResponce = tracksByArtistResponce;
    }
}
