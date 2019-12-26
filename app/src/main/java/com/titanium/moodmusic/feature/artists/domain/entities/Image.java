package com.titanium.moodmusic.feature.artists.domain.entities;

public class Image {

    private final String url;
    private final String size;

    public Image(String url, String size) {
        this.url = url;
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public String getSize() {
        return size;
    }
}
