package com.titanium.moodmusic.feature.tracks.data.model.responces;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.feature.tracks.data.model.chart.ChartTrackModelList;

public class TopChartTracksResponce {

    @SerializedName("tracks")
    private ChartTrackModelList tracksResponce;

    public ChartTrackModelList getTracksResponce() {
        return tracksResponce;
    }
}
