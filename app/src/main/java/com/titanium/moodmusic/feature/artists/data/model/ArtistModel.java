package com.titanium.moodmusic.feature.artists.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistModel {

    @SerializedName("mbid")
    private final String mbid;

    @SerializedName("name")
    private final String name;

    @SerializedName("image")
    private final List<ImageModel> images;

    @SerializedName("streamable")
    private final String streamable;

    @SerializedName("playcount")
    private final String playCount;

    @SerializedName("url")
    private final String url;

    public ArtistModel(String mbid, String name, List<ImageModel> images, String streamable, String playCount, String url) {
        this.mbid = mbid;
        this.name = name;
        this.images = images;
        this.streamable = streamable;
        this.playCount = playCount;
        this.url = url;
    }

    public String getMbid() {
        return mbid;
    }

    public String getName() {
        return name;
    }

    public List<ImageModel> getImages() {
        return images;
    }

    public String getStreamable() {
        return streamable;
    }

    public String getPlayCount() {
        return playCount;
    }

    public String getUrl() {
        return url;
    }
}
