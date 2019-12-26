package com.titanium.moodmusic.app.di.modules;

import com.titanium.moodmusic.app.di.scopes.ApplicationScope;
import com.titanium.moodmusic.component.network.ApiConfiguration;
import com.titanium.moodmusic.component.network.AuthInterceptor;
import com.titanium.moodmusic.component.network.ErrorInterceptor;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    @ApplicationScope
    Retrofit providesRetrofit(OkHttpClient client, Converter.Factory converter, CallAdapter.Factory rxConverter, ApiConfiguration apiConfiguration) {
        return new Retrofit.Builder()
                .baseUrl(apiConfiguration.getBaseUrl())
                .client(client)
                .addConverterFactory(converter)
                .addCallAdapterFactory(rxConverter)
                .build();
    }

    @Provides
    @ApplicationScope
    OkHttpClient providesOkHttpClient(ErrorInterceptor errorInterceptor, AuthInterceptor authInterceptor, HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(errorInterceptor)
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @ApplicationScope
    Converter.Factory providesConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @ApplicationScope
    CallAdapter.Factory providesCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @ApplicationScope
    ErrorInterceptor providesErrorInterceptor() {
        return new ErrorInterceptor();
    }

    @Provides
    @ApplicationScope
    AuthInterceptor providesAuthInterceptor(ApiConfiguration apiConfiguration) {
        return new AuthInterceptor(apiConfiguration.getApiKey());
    }

    @Provides
    @ApplicationScope
    HttpLoggingInterceptor providesLogginInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }
}
