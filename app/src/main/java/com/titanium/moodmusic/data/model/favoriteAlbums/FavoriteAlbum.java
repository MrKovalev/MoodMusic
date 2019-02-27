package com.titanium.moodmusic.data.model.favoriteAlbums;

public class FavoriteAlbum {
    private int albumId;
    private String nameAlbum;
    private String countSongsInAlbum;

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
}
