package com.titanium.moodmusic.ui.fragments.favoriteAlbums;

import com.titanium.moodmusic.data.db.entity.FavoriteAlbumTable;
import com.titanium.moodmusic.data.model.favoriteAlbums.FavoriteAlbum;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FavoriteAlbumsPresenter implements IFavoriteAlbumsPresenter {

    IFavoriteAlbumsView iFavoriteAlbumsView;
    IFavoriteAlbumsInteractor iFavoriteAlbumsInteractor;
    private Disposable disposable;


    public FavoriteAlbumsPresenter(IFavoriteAlbumsView iFavoriteAlbumsView, IFavoriteAlbumsInteractor iFavoriteAlbumsInteractor) {
        this.iFavoriteAlbumsView = iFavoriteAlbumsView;
        this.iFavoriteAlbumsInteractor = iFavoriteAlbumsInteractor;
    }

    @Override
    public void getFavoriteAlbums() {
        disposable = iFavoriteAlbumsInteractor.getFavoriteAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FavoriteAlbumTable>>() {
                    @Override
                    public void accept(List<FavoriteAlbumTable> favoriteAlbumTableList) throws Exception {
                        iFavoriteAlbumsView.loadAlbums(castAlbumsFromDb(favoriteAlbumTableList));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void saveAlbumsToDatabase(final List<FavoriteAlbum> favoriteAlbumList) {
        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                iFavoriteAlbumsInteractor.saveAlbumsToDatabase(favoriteAlbumList);
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
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


    private List<FavoriteAlbum> castAlbumsFromDb(List<FavoriteAlbumTable> favoriteAlbumTableList) {
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

    @Override
    public void onDestroy() {
        disposeRequest();
    }

    private void disposeRequest() {
        if (disposable != null) {
            if (!disposable.isDisposed())
                disposable.dispose();
        }
    }
}
