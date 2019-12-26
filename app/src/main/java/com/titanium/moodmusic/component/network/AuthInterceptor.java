package com.titanium.moodmusic.component.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final String apiKey;

    public AuthInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        HttpUrl.Builder urlBuilder = originalRequest.url().newBuilder();

        urlBuilder.addQueryParameter("api_key", apiKey)
                .addQueryParameter("format", "json");

        Request newRequest = originalRequest.newBuilder()
                .url(urlBuilder.build())
                .build();

        return chain.proceed(newRequest);
    }
}
