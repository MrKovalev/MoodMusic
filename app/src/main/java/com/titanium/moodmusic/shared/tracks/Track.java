package com.titanium.moodmusic.shared.tracks;


import java.util.Objects;

public class Track {

    private final String mbid;
    private final String name;
    private final String playCount;
    private final String url;
    private final String artistMbid;
    private final String artistName;
    private final String artistUrl;

    public Track(String mbid, String name, String playCount, String url, String artistMbid, String artistName, String artistUrl) {
        this.mbid = mbid;
        this.name = name;
        this.playCount = playCount;
        this.url = url;
        this.artistMbid = artistMbid;
        this.artistName = artistName;
        this.artistUrl = artistUrl;
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

    public String getArtistMbid() {
        return artistMbid;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistUrl() {
        return artistUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return mbid.equals(track.mbid) &&
                name.equals(track.name) &&
                playCount.equals(track.playCount) &&
                url.equals(track.url) &&
                artistMbid.equals(track.artistMbid) &&
                artistName.equals(track.artistName) &&
                artistUrl.equals(track.artistUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbid, name, playCount, url, artistMbid, artistName, artistUrl);
    }
}