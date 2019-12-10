package com.titanium.moodmusic.feature.tracks.data.model;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.feature.tracks.data.model.TrackList;

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
