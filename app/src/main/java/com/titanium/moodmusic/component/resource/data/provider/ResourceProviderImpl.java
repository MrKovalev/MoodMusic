package com.titanium.moodmusic.component.resource.data.provider;

import android.content.Context;

import javax.inject.Inject;

public class ResourceProviderImpl implements ResourceProvider {

    private final Context context;

    @Inject
    ResourceProviderImpl(Context context) {
        this.context = context;
    }

    @Override
    public String getStringById(int resId) {
        return context.getString(resId);
    }
}
