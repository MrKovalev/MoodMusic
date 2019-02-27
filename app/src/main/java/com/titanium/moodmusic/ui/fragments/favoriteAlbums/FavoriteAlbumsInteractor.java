package com.titanium.moodmusic.ui.fragments.favoriteAlbums;

import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;
import com.titanium.moodmusic.ui.fragments.artists.ArtistsGenerator;

import java.util.List;

public class FavoriteAlbumsInteractor implements IFavoriteAlbumsInteractor {
    @Override
    public List<FavoriteAlbum> getFavoriteAlbums() {
        return ArtistsGenerator.getAlbums();
    }

    @Override
    public List<FavoriteAlbum> searchAlbum(String nameAlbum) {
        return null;
    }
}
