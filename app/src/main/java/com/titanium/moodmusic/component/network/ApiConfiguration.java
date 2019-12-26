package com.titanium.moodmusic.component.network;

import android.content.Context;

import com.titanium.moodmusic.R;

import javax.inject.Inject;

public class ApiConfiguration {

    private final String baseUrl;
    private final String apiKey;

    @Inject
    public ApiConfiguration(Context context) {
        this.baseUrl = context.getString(R.string.base_url);
        this.apiKey = context.getString(R.string.api_key);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }
}
