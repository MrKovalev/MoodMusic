package com.titanium.moodmusic.feature.tracks.data.model.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchTrackModelList {

    @SerializedName("track")
    private List<SearchTrackModel> searchTrackModels;

    public List<SearchTrackModel> getSearchTrackModels() {
        return searchTrackModels;
    }
}
