package com.titanium.moodmusic.data.model.responces;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.data.model.artists.ArtistList;

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
