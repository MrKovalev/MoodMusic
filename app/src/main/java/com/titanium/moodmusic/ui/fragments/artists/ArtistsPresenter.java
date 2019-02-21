package com.titanium.moodmusic.ui.fragments.artists;

import android.util.Log;

import com.titanium.moodmusic.data.api.Constants;
import com.titanium.moodmusic.data.model.Artist;
import com.titanium.moodmusic.data.model.ArtistsResponce;

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
    public void getTopChartArtists(int limit, String apiKey) {
        Log.d("TAG","load data from presenter");
        iArtistsView.showProgress();
        iArtistsView.hideEmpty();

        Call<ArtistsResponce> listCall = iArtistsInteractor.getTopChartArtists(Constants.TOP_ITEMS_LIMIT,Constants.API_KEY);
        listCall.enqueue(new Callback<ArtistsResponce>() {
            @Override
            public void onResponse(Call<ArtistsResponce> call, Response<ArtistsResponce> response) {
                //push to view
                iArtistsView.loadArtists(response.body().getArtistListResponce().getArtistList());
                iArtistsView.hideProgress();
            }

            @Override
            public void onFailure(Call<ArtistsResponce> call, Throwable t) {
               iArtistsView.showError();
            }
        });
    }
}
