package com.titanium.moodmusic.shared.albums.domain.entiries;

import com.titanium.moodmusic.shared.tracks.Track;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Album implements Serializable {

    private final int albumId;
    private String nameAlbum;
    private final String description;
    private final List<Track> trackList;

    public Album(int albumId, String nameAlbum, String description, List<Track> tracks) {
        this.albumId = albumId;
        this.nameAlbum = nameAlbum;
        this.description = description;
        this.trackList = tracks;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getDescription() {
        return description;
    }

    public List<Track> getTracks() {
        return trackList;
    }

    public void addNewTrack(Track track){
        this.trackList.add(track);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return albumId == album.albumId &&
                nameAlbum.equals(album.nameAlbum) &&
                description.equals(album.description) &&
                trackList.equals(album.trackList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, nameAlbum, description, trackList);
    }
}