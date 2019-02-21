package com.titanium.moodmusic.data.api.retrofit;

import com.titanium.moodmusic.data.api.Constants;
import com.titanium.moodmusic.network.LastFmAPI;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LastFmRetrofitClient {
    private static LastFmRetrofitClient lastFmRetrofitClientInstance;
    private Retrofit retrofit;

    private LastFmRetrofitClient(){
        retrofit = retrofitBuilder();
    }

    public static LastFmRetrofitClient getInstance(){
        if (lastFmRetrofitClientInstance == null){
            lastFmRetrofitClientInstance = new LastFmRetrofitClient();
        }

        return lastFmRetrofitClientInstance;
    }

    private Retrofit retrofitBuilder(){
        return new Retrofit
                .Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getOkHttpClient(){
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        return client.build();
    }

    public LastFmAPI getLastFmApi(){
        return retrofit.create(LastFmAPI.class);
    }

}
