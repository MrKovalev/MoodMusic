package com.titanium.moodmusic.data.model.responces;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.data.model.tracks.TrackList;

public class TopChartTracksResponce {

    @SerializedName("tracks")
    private TrackList tracksResponce;

    public TrackList getTracksResponce() {
        return tracksResponce;
    }

    public void setTracksResponce(TrackList tracksResponce) {
        this.tracksResponce = tracksResponce;
    }
}
