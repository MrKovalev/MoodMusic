package com.titanium.moodmusic.feature.tracks.data.model.chart;

import com.google.gson.annotations.SerializedName;

public class ArtistModel {

    @SerializedName("mbid")
    private final String mbid;

    @SerializedName("name")
    private final String name;

    @SerializedName("url")
    private final String url;

    public ArtistModel(String mbid, String name, String url) {
        this.mbid = mbid;
        this.name = name;
        this.url = url;
    }

    public String getMbid() {
        return mbid;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
