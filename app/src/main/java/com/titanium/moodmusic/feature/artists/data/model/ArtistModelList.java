package com.titanium.moodmusic.feature.artists.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistModelList {

    @SerializedName("artist")
    private List<ArtistModel> artistModelList;

    public List<ArtistModel> getArtistModelList() {
        return artistModelList;
    }
}
