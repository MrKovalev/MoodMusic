package com.titanium.moodmusic.feature.tracks.data.model.chart;

import com.google.gson.annotations.SerializedName;

public class ChartTrackModel {

    @SerializedName("mbid")
    private final String mbid;

    @SerializedName("name")
    private final String name;

    @SerializedName("playcount")
    private final String playCount;

    @SerializedName("url")
    private final String url;

    @SerializedName("artist")
    private final ArtistModel artist;

    public ChartTrackModel(String mbid, String name, String playCount, String url, ArtistModel artist) {
        this.mbid = mbid;
        this.name = name;
        this.playCount = playCount;
        this.url = url;
        this.artist = artist;
    }

    public String getMbid() {
        return mbid;
    }

    public String getName() {
        return name;
    }

    public String getPlayCount() {
        return playCount;
    }

    public String getUrl() {
        return url;
    }

    public ArtistModel getArtist() {
        return artist;
    }
}
