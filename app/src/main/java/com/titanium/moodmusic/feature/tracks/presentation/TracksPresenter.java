package com.titanium.moodmusic.feature.tracks.presentation;

import com.titanium.moodmusic.component.presentation.BasePresenter;
import com.titanium.moodmusic.feature.tracks.domain.interactors.TracksInteractor;
import com.titanium.moodmusic.shared.albums.domain.entiries.Album;
import com.titanium.moodmusic.shared.albums.domain.interactors.AlbumsInteractor;
import com.titanium.moodmusic.shared.error.Message;
import com.titanium.moodmusic.shared.error.handler.ErrorHandler;
import com.titanium.moodmusic.shared.search.domain.usecases.SearchQueryInteractor;
import com.titanium.moodmusic.shared.tracks.Track;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;

@InjectViewState
public class TracksPresenter extends BasePresenter<TracksView> {

    private final TracksInteractor tracksInteractor;
    private final AlbumsInteractor albumsInteractor;
    private final SearchQueryInteractor<String> searchQueryInteractor;
    private final ErrorHandler errorHandler;

    private static final int SEARCH_DEFAULT_PAGE = 1;
    private static final int TRACKS_LIMIT = 15;

    private int trackPageCounter = 1;

    @Inject
    public TracksPresenter(TracksInteractor tracksInteractor, AlbumsInteractor albumsInteractor, SearchQueryInteractor<String> searchQueryInteractor, ErrorHandler errorHandler) {
        this.tracksInteractor = tracksInteractor;
        this.albumsInteractor = albumsInteractor;
        this.searchQueryInteractor = searchQueryInteractor;
        this.errorHandler = errorHandler;
    }

    @Override
    protected void onFirstViewAttach() {
        onLoadTopChartTracks();
        initSearchQuery();
    }

    public void onRefreshTracks() {
        trackPageCounter = 1;

        getViewState().clearTracks();
        onLoadTopChartTracks();
    }

    public void onSearchTrack(String query) {
        searchQueryInteractor.add(query);
    }

    private void initSearchQuery() {
        Disposable disposable = searchQueryInteractor.getObservable()
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterNext(query -> {
                    getViewState().clearTracks();
                    getViewState().showProgress();
                })
                .switchMap(this::searchTracks)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successSearched, this::handleError);

        unsubscribeOnDestroy(disposable);
    }

    private Observable<List<Track>> searchTracks(String query) {
        return tracksInteractor.searchTrack(TRACKS_LIMIT, SEARCH_DEFAULT_PAGE, query, null)
                .subscribeOn(Schedulers.io())
                .toObservable();
    }

    private void successSearched(List<Track> tracks) {
        getViewState().hideProgress();
        getViewState().hideLoadingItem();
        getViewState().showSearchedTracks(tracks);
    }

    public void onLoadTopChartTracks() {
        getViewState().showProgress();

        Disposable disposable = tracksInteractor.getTopChartTracks(trackPageCounter, TRACKS_LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(getViewState()::hideProgress)
                .subscribe(this::processChunk, this::handleError);

        unsubscribeOnDestroy(disposable);
    }

    private void processChunk(List<Track> trackList) {
        getViewState().showLoadingItem();
        getViewState().showLoadedTracks(trackList);

        trackPageCounter++;
    }

    public void onOpenTrackDetailClicked(Track track) {
        getViewState().openTrackDetail(track);
    }

    public void onAddTrackClicked(Track selectedTrack) {
        getViewState().showProgress();

        Disposable disposable = albumsInteractor.getFavoriteAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(getViewState()::hideProgress)
                .subscribe(
                        albums -> getViewState().showAddTrackDialog(selectedTrack, albums),
                        this::handleError
                );

        unsubscribeOnDestroy(disposable);
    }

    public void onSaveTrack(Album selectedAlbum, Track selectedTrack) {
        getViewState().showProgress();
        selectedAlbum.addNewTrack(selectedTrack);

        Disposable disposable = albumsInteractor.updateAlbumWithNewTracks(selectedAlbum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(getViewState()::hideProgress)
                .subscribe(
                        getViewState()::showSuccessSavedTrack,
                        this::handleError
                );

        unsubscribeOnDestroy(disposable);
    }

    private void handleError(Throwable throwable) {
        Message message = errorHandler.processError(throwable);

        getViewState().hideProgress();
        getViewState().showError(message);
    }
}