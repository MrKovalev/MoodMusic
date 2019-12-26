package com.titanium.moodmusic.component.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.titanium.moodmusic.shared.tracks.Track;

import java.util.List;

@Entity(tableName = AlbumEntity.TABLE)
public class AlbumEntity {

    public static final String TABLE = "album";
    public static final String ID = "id";
    public static final String NAME_ALBUM = "name_album";
    public static final String COUNT_SONGS = "count_songs";
    public static final String TRACKS = "tracks";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private final int idAlbum;

    @ColumnInfo(name = NAME_ALBUM)
    private final String nameAlbum;

    @ColumnInfo(name = COUNT_SONGS)
    private final String description;

    @ColumnInfo(name = TRACKS)
    private final List<Track> tracks;

    public AlbumEntity(int idAlbum, String nameAlbum, String description, List<Track> tracks) {
        this.idAlbum = idAlbum;
        this.nameAlbum = nameAlbum;
        this.description = description;
        this.tracks = tracks;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public String getDescription() {
        return description;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}