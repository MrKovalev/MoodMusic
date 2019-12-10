package com.titanium.moodmusic.feature.artists.data.model;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.feature.artists.data.model.ArtistList;

public class TopChartArtistsResponce {

    @SerializedName("artists")
    private ArtistList artistListResponce;

    public ArtistList getArtistListResponce() {
        return artistListResponce;
    }

    public void setArtistListResponce(ArtistList artistListResponce) {
        this.artistListResponce = artistListResponce;
    }
}
