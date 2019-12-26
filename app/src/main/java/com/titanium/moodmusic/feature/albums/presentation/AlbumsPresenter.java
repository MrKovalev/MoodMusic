package com.titanium.moodmusic.feature.albums.presentation;

import com.titanium.moodmusic.component.presentation.BasePresenter;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;
import com.titanium.moodmusic.shared.albums.domain.interactors.AlbumsInteractor;
import com.titanium.moodmusic.shared.error.Message;
import com.titanium.moodmusic.shared.error.handler.ErrorHandler;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;

@InjectViewState
public class AlbumsPresenter extends BasePresenter<AlbumsView> {

    private final AlbumsInteractor albumsInteractor;
    private final ErrorHandler errorHandler;

    @Inject
    public AlbumsPresenter(AlbumsInteractor albumsInteractor, ErrorHandler errorHandler) {
        this.albumsInteractor = albumsInteractor;
        this.errorHandler = errorHandler;
    }

    @Override
    protected void onFirstViewAttach() {
        getFavoriteAlbums();
    }

    public void getFavoriteAlbums() {
        getViewState().showProgress();

        Disposable disposable = albumsInteractor.getFavoriteAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(getViewState()::hideProgress)
                .subscribe(getViewState()::showAlbums, this::handleError);

        unsubscribeOnDestroy(disposable);
    }

    public void onOpenAlbumClicked(Album album) {
        getViewState().openAlbumDetails(album);
    }

    public void onAddNewAlbumClicked() {
        getViewState().showAddNewAlbumDialog();
    }

    public void onSelectMenuAlbumClicked(Album album, int position) {
        getViewState().showChooseActionDialog(album, position);
    }

    public void onEditAlbumSelected(Album album) {
        getViewState().showRenameAlbumDialog(album);
    }

    public void onDeleteAlbumSelected(int position, Album album) {
        Disposable disposable = albumsInteractor.deleteAlbum(album.getAlbumId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> getViewState().deleteAlbum(position),
                        this::handleError
                );

        unsubscribeOnDestroy(disposable);
    }

    public void onAddAlbum(Album album) {
        Disposable disposable = albumsInteractor.saveAlbum(album)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> getViewState().addAlbum(album),
                        this::handleError
                );

        unsubscribeOnDestroy(disposable);
    }

    public void onEditAlbum(Album album) {
        getViewState().editAlbum(album);
        //TODO добавить обновление в базе
    }

    private void handleError(Throwable throwable) {
        Message message = errorHandler.processError(throwable);
        getViewState().showError(message);
    }
}