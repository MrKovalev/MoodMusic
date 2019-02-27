package com.titanium.moodmusic.ui.fragments.favoriteAlbums;

import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;

import java.util.List;

public class FavoriteAlbumsPresenter implements IFavoriteAlbumsPresenter {

    IFavoriteAlbumsView iFavoriteAlbumsView;
    IFavoriteAlbumsInteractor iFavoriteAlbumsInteractor;

    public FavoriteAlbumsPresenter(IFavoriteAlbumsView iFavoriteAlbumsView, IFavoriteAlbumsInteractor iFavoriteAlbumsInteractor) {
        this.iFavoriteAlbumsView = iFavoriteAlbumsView;
        this.iFavoriteAlbumsInteractor = iFavoriteAlbumsInteractor;
    }

    @Override
    public void getFavoriteAlbums() {
        List<FavoriteAlbum> favoriteAlbums = iFavoriteAlbumsInteractor.getFavoriteAlbums();
        iFavoriteAlbumsView.loadAlbums(favoriteAlbums);
    }

    @Override
    public void searchAlbum(String nameAlbum) {
    }
}
