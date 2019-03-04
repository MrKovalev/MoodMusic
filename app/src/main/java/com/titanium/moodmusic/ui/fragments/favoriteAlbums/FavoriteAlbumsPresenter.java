package com.titanium.moodmusic.ui.fragments.favoriteAlbums;

import android.util.Log;

import com.titanium.moodmusic.data.db.AsyncDataLoader;
import com.titanium.moodmusic.data.db.entity.FavoriteAlbumTable;
import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAlbumsPresenter implements IFavoriteAlbumsPresenter {

    IFavoriteAlbumsView iFavoriteAlbumsView;
    IFavoriteAlbumsInteractor iFavoriteAlbumsInteractor;
    AsyncDataLoader asyncDataLoader;

    public FavoriteAlbumsPresenter(IFavoriteAlbumsView iFavoriteAlbumsView, IFavoriteAlbumsInteractor iFavoriteAlbumsInteractor, AsyncDataLoader asyncDataLoader) {
        this.iFavoriteAlbumsView = iFavoriteAlbumsView;
        this.iFavoriteAlbumsInteractor = iFavoriteAlbumsInteractor;
        this.asyncDataLoader = asyncDataLoader;
    }

    @Override
    public void getFavoriteAlbums() {

        asyncDataLoader.setAsyncResponceResult(new AsyncDataLoader.AsyncResponceResult() {
            @Override
            public void onTaskComplete(List<FavoriteAlbumTable> result) {
                Log.d("TAG","interface getResult");
                Log.d("TAG","result = "+result.size());
                iFavoriteAlbumsView.loadAlbums(castAlbumsFromDb(result));
            }
        });

        asyncDataLoader.execute();
    }

    @Override
    public void saveAlbumsToDatabase(List<FavoriteAlbum> favoriteAlbumList) {
        Log.d("TAG","save to db, presenter");
        iFavoriteAlbumsInteractor.saveAlbumsToDatabase(favoriteAlbumList);
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


    private List<FavoriteAlbum> castAlbumsFromDb(List<FavoriteAlbumTable> favoriteAlbumTableList){
        List<FavoriteAlbum> favoriteAlbums = new ArrayList<>();

        for (FavoriteAlbumTable item : favoriteAlbumTableList) {
            FavoriteAlbum favoriteAlbum = new FavoriteAlbum();
            favoriteAlbum.setAlbumId(item.getIdAlbum());
            favoriteAlbum.setNameAlbum(item.getNameAlbum());
            favoriteAlbum.setCountSongsInAlbum(item.getCountSongsInAlbum());
            favoriteAlbum.setTrackList(item.getTrackList());

            favoriteAlbums.add(favoriteAlbum);
        }

        return favoriteAlbums;
    }
}
