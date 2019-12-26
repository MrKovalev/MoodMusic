package com.titanium.moodmusic.feature.tracks.data.model.chart;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChartTrackModelList {

    @SerializedName("track")
    private List<ChartTrackModel> chartTrackModels;

    public List<ChartTrackModel> getChartTrackModels() {
        return chartTrackModels;
    }
}
