package com.titanium.moodmusic.feature.artists.data.model;

import com.google.gson.annotations.SerializedName;

public class ImageModel {

    @SerializedName("#text")
    private String url;

    @SerializedName("size")
    private String size;

    public String getUrl() {
        return url;
    }

    public String getSize() {
        return size;
    }
}
