package com.titanium.moodmusic.ui.fragments.artists;

import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;
import com.titanium.moodmusic.data.model.artists.Artist;
import com.titanium.moodmusic.data.model.tracks.Track;

import java.util.ArrayList;
import java.util.List;

public class ArtistsGenerator {

    public static List<Artist> generateArtists(){
        Artist artist = new Artist();
        artist.setName("Ramstain");

        List<Artist> list = new ArrayList<Artist>();
        list.add(artist);

        return list;
    }

    public static List<Track> getTracks(){
        Track track = new Track();
        track.setName("KAKAKAK");
        track.setArtist("fff");

        List<Track> list = new ArrayList<>();
        list.add(track);

        return list;
    }

    public static List<FavoriteAlbum> getAlbums(){
        FavoriteAlbum favoriteAlbum = new FavoriteAlbum(0,"TEST","0 songs");
        favoriteAlbum.setNameAlbum("5gaet");

        List<FavoriteAlbum> list = new ArrayList<>();
        list.add(favoriteAlbum);

        return list;
    }
}
