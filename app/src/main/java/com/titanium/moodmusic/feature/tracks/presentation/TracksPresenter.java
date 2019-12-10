package com.titanium.moodmusic.feature.tracks.presentation;

import com.titanium.moodmusic.feature.tracks.data.model.SearchTrackResponce;
import com.titanium.moodmusic.feature.tracks.data.model.TopChartTracksResponce;
import com.titanium.moodmusic.feature.tracks.data.model.TracksByArtistResponce;
import com.titanium.moodmusic.feature.tracks.data.model.Track;
import com.titanium.moodmusic.feature.tracks.domain.ITracksInteractor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class TracksPresenter implements ITracksPresenter {

    private ITracksView iTracksView;
    private ITracksInteractor iTracksInteractor;
    private Disposable disposable;

    public TracksPresenter(ITracksView iTracksView, ITracksInteractor iTracksInteractor) {
        this.iTracksView = iTracksView;
        this.iTracksInteractor = iTracksInteractor;
    }

    @Override
    public void getTopChartTracks(int page, int limit, String apiKey) {
        disposeRequest();

        iTracksView.showProgress();
        disposable = iTracksInteractor.getTopChartTracks(page, limit, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TopChartTracksResponce, List<Track>>() {
                    @Override
                    public List<Track> apply(TopChartTracksResponce topChartTracksResponce) throws Exception {
                        if (topChartTracksResponce != null && topChartTracksResponce.getTracksResponce() != null) {
                            return topChartTracksResponce.getTracksResponce().getTrackList();
                        }

                        return new ArrayList<Track>();
                    }
                })
                .subscribe(new Consumer<List<Track>>() {
                    @Override
                    public void accept(List<Track> trackList) throws Exception {
                        if (iTracksView != null) {
                            iTracksView.hideProgress();
                            iTracksView.loadTracks(trackList);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iTracksView != null) {
                            iTracksView.hideProgress();
                            iTracksView.showError();
                        }
                    }
                });
    }

    @Override
    public void searchTrack(int limit, int page, String nameTrack, String nameArtist, String apiKey) {
        disposeRequest();

        iTracksView.showProgress();
        disposable = iTracksInteractor.searchTrack(limit, page, nameTrack, nameArtist, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<SearchTrackResponce, List<Track>>() {
                    @Override
                    public List<Track> apply(SearchTrackResponce searchTrackResponce) throws Exception {
                        if (searchTrackResponce != null && searchTrackResponce.getTrackListResponce() != null) {
                            return searchTrackResponce.getTrackListResponce().getTrackListMatches().getTrackList();
                        }

                        return new ArrayList<Track>();
                    }
                })
                .subscribe(new Consumer<List<Track>>() {
                    @Override
                    public void accept(List<Track> trackList) throws Exception {
                        if (iTracksView != null) {
                            iTracksView.hideProgress();
                            iTracksView.searchTracks(trackList);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iTracksView != null) {
                            iTracksView.hideProgress();
                            iTracksView.showError();
                        }
                    }
                });
    }

    @Override
    public void searchTracksByArtist(String nameArtist, String mbid, int limit, int page, String apiKey) {
        disposeRequest();

        iTracksView.showProgress();
        disposable = iTracksInteractor.searchTracksByArtist(nameArtist, mbid, limit, page, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TracksByArtistResponce, List<Track>>() {
                    @Override
                    public List<Track> apply(TracksByArtistResponce tracksByArtistResponce) throws Exception {
                        if (tracksByArtistResponce != null && tracksByArtistResponce.getTracksByArtistResponce() != null) {
                            return tracksByArtistResponce.getTracksByArtistResponce().getTrackList();
                        }

                        return new ArrayList<Track>();
                    }
                })
                .subscribe(new Consumer<List<Track>>() {
                    @Override
                    public void accept(List<Track> trackList) throws Exception {
                        if (iTracksView != null) {
                            iTracksView.hideProgress();
                            iTracksView.searchTracksByArtist(trackList);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iTracksView != null) {
                            iTracksView.hideProgress();
                            iTracksView.showError();
                        }
                    }
                });
    }

    @Override
    public void openTrackDetail(List<Track> trackList, Track track, int position) {
        iTracksView.openTrackDetail(trackList, track, position);
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
