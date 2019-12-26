package com.titanium.moodmusic.feature.tracks.data.model.responces;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.feature.tracks.data.model.search.SearchTrackModelList;

public class SearchTracksByArtistResponce {

    @SerializedName("toptracks")
    private SearchTrackModelList tracksByArtistResponce;

    public SearchTrackModelList getTracksByArtistResponce() {
        return tracksByArtistResponce;
    }
}
