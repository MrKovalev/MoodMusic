package com.titanium.moodmusic.feature.artists.domain.entities;

import java.util.List;
import java.util.Objects;

public class Artist {

    private final String mbid;
    private final String name;
    private final List<Image> images;
    private final String streamable;
    private final String playCount;
    private final String url;

    public Artist(String mbid, String name, List<Image> images, String streamable, String playCount, String url) {
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

    public List<Image> getImages() {
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

    public String getDefaultImageUrl() {
        if (getImages() != null && !images.isEmpty()) {
            for (Image image : images) {
                if (image.getSize().equalsIgnoreCase("mega")) {
                    return image.getUrl();
                }
            }
        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return mbid.equals(artist.mbid) &&
                name.equals(artist.name) &&
                images.equals(artist.images) &&
                streamable.equals(artist.streamable) &&
                playCount.equals(artist.playCount) &&
                url.equals(artist.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbid, name, images, streamable, playCount, url);
    }
}
