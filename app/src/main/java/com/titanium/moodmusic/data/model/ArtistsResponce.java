package com.titanium.moodmusic.data.model;

import com.google.gson.annotations.SerializedName;

public class ArtistsResponce {

    @SerializedName("artists")
    private ArtistList artistListResponce;

    public ArtistList getArtistListResponce() {
        return artistListResponce;
    }

    public void setArtistListResponce(ArtistList artistListResponce) {
        this.artistListResponce = artistListResponce;
    }
}
