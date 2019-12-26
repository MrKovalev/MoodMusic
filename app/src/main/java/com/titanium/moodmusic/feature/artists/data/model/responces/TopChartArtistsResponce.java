package com.titanium.moodmusic.feature.artists.data.model.responces;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.feature.artists.data.model.ArtistModelList;

public class TopChartArtistsResponce {

    @SerializedName("artists")
    private ArtistModelList artistModelListResponce;

    public ArtistModelList getArtistModelListResponce() {
        return artistModelListResponce;
    }
}
