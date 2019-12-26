package com.titanium.moodmusic.feature.artists.presentation;

import com.titanium.moodmusic.component.presentation.BasePresenter;
import com.titanium.moodmusic.feature.artists.domain.entities.Artist;
import com.titanium.moodmusic.feature.artists.domain.interactors.ArtistsInteractor;
import com.titanium.moodmusic.shared.error.Message;
import com.titanium.moodmusic.shared.error.handler.ErrorHandler;
import com.titanium.moodmusic.shared.search.domain.usecases.SearchQueryInteractor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;

@InjectViewState
public class ArtistsPresenter extends BasePresenter<ArtistsView> {

    private final ArtistsInteractor artistsInteractor;
    private final SearchQueryInteractor<String> searchQueryInteractor;
    private final ErrorHandler errorHandler;

    private int artistsPageCounter = 1;
    private boolean isSearchActive = false;

    private static final int SEARCH_DEFAULT_PAGE = 1;
    private static final int ARTISTS_LIMIT = 10;

    @Inject
    public ArtistsPresenter(ArtistsInteractor artistsInteractor, SearchQueryInteractor<String> searchQueryInteractor, ErrorHandler errorHandler) {
        this.artistsInteractor = artistsInteractor;
        this.searchQueryInteractor = searchQueryInteractor;
        this.errorHandler = errorHandler;
    }

    @Override
    protected void onFirstViewAttach() {
        onLoadTopChartArtists();
        initSearchQuery();
    }

    public void onRefreshArtists() {
        isSearchActive = false;
        artistsPageCounter = 1;

        getViewState().clearArtists();
        onLoadTopChartArtists();
    }

    public void onSearchArtists(String query) {
        isSearchActive = true;
        searchQueryInteractor.add(query);
    }

    private void initSearchQuery() {
        Disposable disposable = searchQueryInteractor.getObservable()
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(query -> {
                    getViewState().clearArtists();
                    getViewState().showProgress();
                })
                .switchMap(this::searchArtists)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successSearched, this::handleError);

        unsubscribeOnDestroy(disposable);
    }

    private Observable<List<Artist>> searchArtists(String query) {
        return artistsInteractor.searchArtist(SEARCH_DEFAULT_PAGE, ARTISTS_LIMIT, query)
                .subscribeOn(Schedulers.io())
                .toObservable();
    }

    private void successSearched(List<Artist> artists) {
        getViewState().hideProgress();
        getViewState().hideLoadingItem();
        getViewState().showSearchedArtists(artists);
    }

    public void onLoadTopChartArtists() {
        if (!isSearchActive) {
            getViewState().showProgress();

            Disposable disposable = artistsInteractor.getTopChartArtists(artistsPageCounter, ARTISTS_LIMIT)
                    .subscribeOn(Schedulers.io())
                    .flattenAsObservable(artists -> artists)
                    .take(ARTISTS_LIMIT)//Костыль. Cервер last.fm тупит и иногда не возвращает список согласно лимиту, берем только порциями по 10
                    .toList()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(getViewState()::hideProgress)
                    .subscribe(this::processChunk, this::handleError);

            unsubscribeOnDestroy(disposable);
        }
    }

    private void processChunk(List<Artist> artistList) {
        getViewState().showLoadingItem();
        getViewState().showChartArtists(artistList);

        artistsPageCounter++;
    }

    private void handleError(Throwable throwable) {
        Message message = errorHandler.processError(throwable);

        getViewState().hideProgress();
        getViewState().showError(message);
    }
}
