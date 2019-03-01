package com.titanium.moodmusic.ui.fragments.artists;

import android.util.Log;

import com.titanium.moodmusic.data.model.artists.Artist;
import com.titanium.moodmusic.data.model.responces.SearchArtistResponce;
import com.titanium.moodmusic.data.model.responces.TopChartArtistsResponce;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistsPresenter implements IArtistsPresenter {

    IArtistsView iArtistsView;
    IArtistsInteractor iArtistsInteractor;

    public ArtistsPresenter(IArtistsView iArtistsView, IArtistsInteractor iArtistsInteractor) {
        this.iArtistsView = iArtistsView;
        this.iArtistsInteractor = iArtistsInteractor;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getTopChartArtists(int page, int limit, String apiKey) {
        iArtistsView.showProgress();
        iArtistsView.hideEmpty();

        Call<TopChartArtistsResponce> listCall = iArtistsInteractor.getTopChartArtists(page, limit, apiKey);
        listCall.enqueue(new Callback<TopChartArtistsResponce>() {
            @Override
            public void onResponse(Call<TopChartArtistsResponce> call, Response<TopChartArtistsResponce> response) {

                if (response.isSuccessful()) {
                    List<Artist> loadedArtistsList = new ArrayList<>();

                    try {
                        loadedArtistsList = response.body().getArtistListResponce().getArtistList();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    iArtistsView.loadArtists(loadedArtistsList);
                    iArtistsView.hideProgress();
                } else {

                    iArtistsView.showError();
                    iArtistsView.hideProgress();
                }
            }

            @Override
            public void onFailure(Call<TopChartArtistsResponce> call, Throwable t) {
                t.printStackTrace();
                iArtistsView.showError();
                iArtistsView.hideProgress();
            }
        });
    }

    @Override
    public void searchArtist(int page, int limit, String name, String apiKey) {
        iArtistsView.showProgress();
        iArtistsView.hideEmpty();

        Log.d("TAG", name);
        Call<SearchArtistResponce> listCall = iArtistsInteractor.searchArtist(page, limit, name, apiKey);
        listCall.enqueue(new Callback<SearchArtistResponce>() {
            @Override
            public void onResponse(Call<SearchArtistResponce> call, Response<SearchArtistResponce> response) {

                if (response.isSuccessful()) {
                    List<Artist> loadedArtistsList = new ArrayList<>();

                    try {
                        loadedArtistsList = response.body()
                                .getSearchArtist()
                                .getArtistListMatches()
                                .getArtistList();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    iArtistsView.hideProgress();
                    iArtistsView.searchArtists(loadedArtistsList);
                } else {
                    Log.d("TAG", "UNKNOWN ERROR 3");
                    iArtistsView.showError();
                    iArtistsView.hideProgress();
                }
            }

            @Override
            public void onFailure(Call<SearchArtistResponce> call, Throwable t) {
                t.printStackTrace();
                iArtistsView.showError();
                iArtistsView.hideProgress();
            }
        });
        //iArtistsView.searchArtists(ArtistsGenerator.generateArtists());
    }
}
