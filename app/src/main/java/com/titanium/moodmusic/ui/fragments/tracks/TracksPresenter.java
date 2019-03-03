package com.titanium.moodmusic.ui.fragments.tracks;

import android.util.Log;

import com.titanium.moodmusic.data.model.responces.TracksByArtistResponce;
import com.titanium.moodmusic.data.model.tracks.Track;
import com.titanium.moodmusic.data.model.responces.SearchTrackResponce;
import com.titanium.moodmusic.data.model.responces.TopChartTracksResponce;
import com.titanium.moodmusic.ui.fragments.artists.ArtistsGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TracksPresenter implements ITracksPresenter{

    private ITracksView iTracksView;
    private ITracksInteractor iTracksInteractor;

    public TracksPresenter(ITracksView iTracksView, ITracksInteractor iTracksInteractor) {
        this.iTracksView = iTracksView;
        this.iTracksInteractor = iTracksInteractor;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getTopChartTracks(int page, int limit, String apiKey) {
        iTracksView.showProgress();
        Call<TopChartTracksResponce> listCall = iTracksInteractor.getTopChartTracks(page,limit,apiKey);
        listCall.enqueue(new Callback<TopChartTracksResponce>() {
            @Override
            public void onResponse(Call<TopChartTracksResponce> call, Response<TopChartTracksResponce> response) {

                if (response.isSuccessful()){
                    List<Track> loadedTrackList = new ArrayList<>();

                    try {
                        loadedTrackList = response.body().getTracksResponce().getTrackList();
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                    iTracksView.loadTracks(loadedTrackList);
                    iTracksView.hideProgress();
                } else {
                    iTracksView.showError();
                    iTracksView.hideProgress();
                }
            }

            @Override
            public void onFailure(Call<TopChartTracksResponce> call, Throwable t) {
                t.printStackTrace();
                iTracksView.showError();
                iTracksView.hideProgress();
            }
        });

    }

    @Override
    public void searchTrack(int limit, int page, String nameTrack, String nameArtist, String apiKey) {
        iTracksView.showProgress();
        Call<SearchTrackResponce> listcall = iTracksInteractor.searchTrack(limit,page,nameTrack,nameArtist,apiKey);

        listcall.enqueue(new Callback<SearchTrackResponce>() {
            @Override
            public void onResponse(Call<SearchTrackResponce> call, Response<SearchTrackResponce> response) {

                if (response.isSuccessful()){
                    List<Track> loadedTrackList = new ArrayList<>();
                    try {
                        loadedTrackList = response.body()
                                .getTrackListResponce()
                                .getTrackListMatches()
                                .getTrackList();

                    } catch (Exception e){
                        e.printStackTrace();

                    }

                    iTracksView.searchTracks(loadedTrackList);
                    iTracksView.hideProgress();
                } else {
                    iTracksView.showError();
                    iTracksView.hideProgress();
                }
            }

            @Override
            public void onFailure(Call<SearchTrackResponce> call, Throwable t) {
                t.printStackTrace();
                iTracksView.showError();
                iTracksView.hideProgress();
            }
        });
    }


    @Override
    public void searchTracksByArtist(String nameArtist, String  mbid, int limit, int page, String apiKey) {
        iTracksView.showProgress();
        Call<TracksByArtistResponce> listCall = iTracksInteractor.searchTracksByArtist(nameArtist,mbid, limit,page,apiKey);

        listCall.enqueue(new Callback<TracksByArtistResponce>() {
            @Override
            public void onResponse(Call<TracksByArtistResponce> call, Response<TracksByArtistResponce> response) {
                if (response.isSuccessful()){

                    List<Track> trackList = new ArrayList<>();

                    try {
                        trackList = response.body()
                                .getTracksByArtistResponce()
                                .getTrackList();
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                    iTracksView.searchTracksByArtist(trackList);
                    iTracksView.hideProgress();
                } else {
                    iTracksView.hideProgress();
                    iTracksView.showError();
                }
            }

            @Override
            public void onFailure(Call<TracksByArtistResponce> call, Throwable t) {
                t.printStackTrace();
                iTracksView.hideProgress();
                iTracksView.showError();
            }
        });
    }

    @Override
    public void openTrackDetail(List<Track> trackList, Track track, int position) {
        iTracksView.openTrackDetail(trackList,track,position);
    }
}
