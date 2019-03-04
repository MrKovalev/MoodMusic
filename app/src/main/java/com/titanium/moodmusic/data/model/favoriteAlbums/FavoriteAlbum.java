package com.titanium.moodmusic.data.model.favoriteAlbums;

import android.util.Log;

import com.titanium.moodmusic.data.model.tracks.Track;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAlbum {
    private int albumId;
    private String nameAlbum;
    private String countSongsInAlbum;
    private List<Track> trackList = new ArrayList<>();

    public FavoriteAlbum() { }

    public FavoriteAlbum(int albumId, String nameAlbum, String countSongsInAlbum) {
        this.albumId = albumId;
        this.nameAlbum = nameAlbum;
        this.countSongsInAlbum = countSongsInAlbum;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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
        this.trackList.addAll(trackList);
    }

    public void addNewTrack(Track track){
        this.trackList.add(track);
    }

    public void deleteOldTrack(Track track, int positionTrackInAlbum){
        this.trackList.remove(positionTrackInAlbum);
    }
}
