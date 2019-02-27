package com.titanium.moodmusic.data.model.artists;

import com.google.gson.annotations.SerializedName;
import com.titanium.moodmusic.data.model.artists.Artist;

import java.util.List;

public class ArtistList {

    @SerializedName("artist")
    private List<Artist> artistList;

    public List<Artist> getArtistList() {
        return artistList;
    }

    public void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
    }
}
