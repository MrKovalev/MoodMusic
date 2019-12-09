package com.titanium.moodmusic.data.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.titanium.moodmusic.data.model.tracks.Track;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FavoriteAlbumTable {

    @PrimaryKey
    private int idAlbum;
    private String nameAlbum;
    private String countSongsInAlbum;

    @TypeConverters(TrackTypeConverter.class)
    private List<Track> trackList = new ArrayList<>();

    public FavoriteAlbumTable() { }

    public FavoriteAlbumTable(int idAlbum, String nameAlbum, String countSongsInAlbum) {
        this.idAlbum = idAlbum;
        this.nameAlbum = nameAlbum;
        this.countSongsInAlbum = countSongsInAlbum;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getCountSongsInAlbum() {
        return countSongsInAlbum;
    }

    public void setCountSongsInAlbum(String countSongsInAlbum) {
        this.countSongsInAlbum = countSongsInAlbum;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }
}
