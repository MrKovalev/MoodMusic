package com.titanium.moodmusic.feature.artists.presentation;

import com.titanium.moodmusic.feature.artists.data.model.Artist;
import com.titanium.moodmusic.feature.artists.data.model.SearchArtistResponce;
import com.titanium.moodmusic.feature.artists.data.model.TopChartArtistsResponce;
import com.titanium.moodmusic.feature.artists.domain.IArtistsInteractor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ArtistsPresenter implements IArtistsPresenter {

    IArtistsView iArtistsView;
    IArtistsInteractor iArtistsInteractor;
    private Disposable disposable;

    public ArtistsPresenter(IArtistsView iArtistsView, IArtistsInteractor iArtistsInteractor) {
        this.iArtistsView = iArtistsView;
        this.iArtistsInteractor = iArtistsInteractor;
    }

    public void getTopChartArtists(int page, int limit, String apiKey) {
        iArtistsView.showProgress();

        disposable = iArtistsInteractor.getTopChartArtists(page, limit, apiKey)
                .subscribeOn(Schedulers.io())  //запускаем запрос и получаем рузультат в фоновом потоке
                .observeOn(AndroidSchedulers.mainThread()) //возвращаемся в ui поток
                .map(new Function<TopChartArtistsResponce, List<Artist>>() { // через map преобразовываем TopChartArtistsResponce в List<Artist>
                    @Override
                    public List<Artist> apply(TopChartArtistsResponce topChartArtistsResponce) throws Exception {
                        if (topChartArtistsResponce != null && topChartArtistsResponce.getArtistListResponce() != null) {
                            return topChartArtistsResponce.getArtistListResponce().getArtistList();
                        }
                        return new ArrayList<Artist>();
                    }
                })
                .subscribe(new Consumer<List<Artist>>() {    //при успешном получении списка артистов отдаем во View
                    @Override
                    public void accept(List<Artist> artists) throws Exception {
                        if (iArtistsView != null) {
                            iArtistsView.hideProgress();
                            iArtistsView.loadArtists(artists);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {  //выкидываем ошибку при проблемах с ответом
                        if (iArtistsView != null) {
                            iArtistsView.hideProgress();
                            iArtistsView.showError();
                        }
                    }
                });
    }

    @Override
    public void searchArtist(int page, int limit, String name, String apiKey) {
        disposeRequest();

        iArtistsView.showProgress();
        disposable = iArtistsInteractor.searchArtist(page, limit, name, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<SearchArtistResponce, List<Artist>>() {
                    @Override
                    public List<Artist> apply(SearchArtistResponce searchArtistResponce) throws Exception {
                        if (searchArtistResponce != null && searchArtistResponce.getSearchArtist() != null) {
                            return searchArtistResponce.getSearchArtist().getArtistListMatches().getArtistList();
                        }

                        return new ArrayList<Artist>();
                    }
                })
                .subscribe(new Consumer<List<Artist>>() {
                    @Override
                    public void accept(List<Artist> artists) throws Exception {
                        if (iArtistsView != null) {
                            iArtistsView.hideProgress();
                            iArtistsView.searchArtists(artists);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iArtistsView != null) {
                            iArtistsView.hideProgress();
                            iArtistsView.showError();
                        }
                    }
                });
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
