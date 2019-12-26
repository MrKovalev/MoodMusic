package com.titanium.moodmusic.feature.tracks.data.model.search;

import com.google.gson.annotations.SerializedName;

public class SearchTrackModel {

    @SerializedName("mbid")
    private final String mbid;

    @SerializedName("name")
    private final String name;

    @SerializedName("playcount")
    private final String playCount;

    @SerializedName("url")
    private final String url;

    @SerializedName("artist")
    private final String artist;

    public SearchTrackModel(String mbid, String name, String playCount, String url, String artist) {
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

    public String getArtist() {
        return artist;
    }
}
