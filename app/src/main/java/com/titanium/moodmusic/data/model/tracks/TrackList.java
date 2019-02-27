package com.titanium.moodmusic.data.model.tracks;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.data.model.tracks.Track;

import java.util.List;

public class TrackList {

    @SerializedName("track")
    List<Track> trackList;

    public List<Track> getTrackList() {
        return trackList;
    }
    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }
}
