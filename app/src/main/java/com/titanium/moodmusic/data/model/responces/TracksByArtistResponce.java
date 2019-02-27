package com.titanium.moodmusic.data.model.responces;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.data.model.tracks.TrackList;

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
