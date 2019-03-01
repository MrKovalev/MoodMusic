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

    @Override
    public void addFavoriteAlbum(FavoriteAlbum favoriteAlbum) {
        iFavoriteAlbumsView.addAlbum(favoriteAlbum);
    }

    @Override
    public void editFavoriteAlbum(FavoriteAlbum favoriteAlbum) {
        iFavoriteAlbumsView.editAlbum(favoriteAlbum);
    }

    @Override
    public void deleteFavoriteAlbum(int position) {
        iFavoriteAlbumsView.deleteAlbum(position);
    }
}
